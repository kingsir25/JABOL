package cobol.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cobol.lang.words.GroupItem;
import cobol.lang.words.ChildItem;
import cobol.lang.words.CobolItem;
public class CopyBook {
   protected List<GroupItem> varTable;

	public List<GroupItem> getVarTable() {
	return varTable;
}

public void setVarTable(List<GroupItem> varTable) {
	this.varTable = varTable;
}

	public static void newVT(Class<?> clazz, CopyBook pgm) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		pgm.varTable = new ArrayList<GroupItem>();
		GroupItem currentItem = null;
		GroupItem rootItem = null;
		String itemName = "";

		GroupItem aheadItem = null;
		GroupItem fatherItem = null;
		GroupItem firstBrotherItem = null;
		int aheadLayNo;
		int currentLayNo;

		int length =0;
		int curentItemIndex = 0;
		for (Field field : fields) {
			// 创建子项目
			if (field.isAnnotationPresent(PIC.class)) {
				PIC p = (PIC) field.getAnnotation(PIC.class);
				itemName = p.name();
				System.out.println("子项目变量：" + itemName);
				currentItem = new ChildItem(Integer.parseInt(p.no()), itemName, p.type(), p.size(), p.value());
				
				length =length + p.size();
			}
			// 创建集团项目
			if (field.isAnnotationPresent(Group.class)) {
				Group g = (Group) field.getAnnotation(Group.class);
				itemName = g.name();
				System.out.println("集团项目变量：" + itemName);
				
				currentItem = new GroupItem(Integer.parseInt(g.no()), itemName);
			}

			if (itemName != "" && currentItem != null) {
				currentLayNo = currentItem.getLayNo();
				// 01层为根项目
				if (currentLayNo == 1) {
					length =0;
					rootItem = currentItem;
				} else if (currentLayNo > 1) {
					if (pgm.varTable.size() > 0) {
						aheadItem = (GroupItem) (pgm.varTable.get(pgm.varTable.size() - 1));
						aheadLayNo = aheadItem.getLayNo();
						if (currentLayNo > aheadLayNo) {
							// 父
							fatherItem = aheadItem;
							// 长子
							firstBrotherItem = currentItem;
							aheadItem.setFirstChildItem(currentItem);
						}
						// 同层父不变，兄不变
						if (currentLayNo == aheadLayNo) {
							currentItem.setFatherItem(fatherItem);
							currentItem.setFirstBrotherItem(firstBrotherItem);
						}
						if (currentLayNo < aheadLayNo) {

							// 从列表当前位置向前找 同层前项目
							for (int i = curentItemIndex - 1; i > 0; i--) {
								GroupItem sameLevelAheadItem = (GroupItem) (pgm.varTable.get(i));
								// 与同层前项目 同兄 同父 ??会不会没有同层项目
								if (sameLevelAheadItem.getLayNo() == currentItem.getLayNo()) {
									firstBrotherItem = (GroupItem) sameLevelAheadItem.getFirstBrotherItem();
									fatherItem = (GroupItem) sameLevelAheadItem.getFatherItem();
									// 找到同层前项目后退出
									break;
								}

							}
						}

					}
				}
				// 同根
				currentItem.setRootItem(rootItem);
				// 同父
				currentItem.setFatherItem(fatherItem);
				// 同兄
				currentItem.setFirstBrotherItem(firstBrotherItem);
				currentItem.setPos(length);
				invoke(pgm, "set" + itemName, currentItem);
				pgm.varTable.add(currentItem);
				curentItemIndex++;
			}
		}
		// 分配空间初始化
		initialization(pgm);
		// 计算长度
		// 计算起始位置
	}
 
   public static void initialization(CopyBook pgm) {
	   List<GroupItem> vars =  pgm.varTable;
	   if (vars == null || vars.size() ==0)  return;
	   String lay01 = "";
	   int length =0;
	   for(int i = vars.size()-1;i>=0;i--) {
		   CobolItem currentItem = pgm.varTable.get(i);
		   
		   if(currentItem instanceof  ChildItem) {
			   if(((ChildItem) currentItem).getLayNo()>1) {
			       length = length + currentItem.getLength();
			   }
			   String sType = ((ChildItem) currentItem).getType();
			   String fillStr =" ";
			   String value = 	((ChildItem) currentItem).getValue();
			   if(sType != null) {
				   if("9".equals(sType.substring(0,1))) {
					   fillStr ="0";
					   //9型在前面填充0, 否则在后边填空格
					   value = 	 String.join("", Collections.nCopies(currentItem.getLength()  - value.length() , fillStr)) + value;
				   }else {
					   value = 	value	+  String.join("", Collections.nCopies(currentItem.getLength() -  value.length(), fillStr));
				   }
			   }
			   //截取指定长度，从后边往前叠加合并
			   lay01= value +  lay01;
		   }
		   //拥有父在上子在下
		   if((currentItem instanceof GroupItem) && !(currentItem instanceof ChildItem)) {
			   currentItem.setLength(length);
			   if(((GroupItem) currentItem).getLayNo()==1) {
				   currentItem.setByteSpace(new String( lay01));
				   lay01 = "";
			   }
		   }
		   
	   }

   }
   /**
    * 
    * @param obj: 方法执行的那个对象. 
    * @param methodName: 类的一个方法的方法名. 该方法也可能是私有方法. 
    * @param args: 调用该方法需要传入的参数
    * @return: 调用方法后的返回值
    *  
    */
     public static Object invoke(Object obj, String methodName, Object ... args) throws Exception{
       //1. 获取 Method 对象
       //   因为getMethod的参数为Class列表类型，所以要把参数args转化为对应的Class类型。
       
       Class [] parameterTypes = new Class[args.length];
       for(int i = 0; i < args.length; i++){
           parameterTypes[i] = args[i].getClass();
//           System.out.println(parameterTypes[i]); 
       }
       
       Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
       //如果使用getDeclaredMethod，就不能获取父类方法，如果使用getMethod，就不能获取私有方法　　　　　　　　　
       //　　　　　
       //2. 执行 Method 方法
       //3. 返回方法的返回值
       return method.invoke(obj, args);
     }
}
