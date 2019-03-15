package cobol.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cobol.lang.words.GroupItem;
import cobol.lang.words.ChildItem;
import cobol.lang.words.CobolItem;

public class Program implements Copyable {
	// 保存变量顺序的变量列表
	public List<Object> varTable;
	// 保存变量名为索引的变量哈希表
	public Map<String, Object> nameSpace;
	// 段落列表
	public List<String> paraList;
	
	@Override
	public void copy(CopyBook cb) {
		// TODO Auto-generated method stub

	}

	public void mergeVT(List<GroupItem> vt) {
		for (GroupItem gi : vt) {
			varTable.add(gi);
		}
	}

	// 取得指定变量的集团项目
	public GroupItem $(String k) {
		return (GroupItem) nameSpace.get(k);
	}
/*
 * 生成段落列表 paraList
 */
	public static void makeParaList(Class<?> clazz, Program pgm) throws Exception {
		Method[] methods = clazz.getDeclaredMethods();
		pgm.paraList = new ArrayList<String>();
		for(Method m:methods) {
			System.out.println(m.getAnnotation(Paragraphs.class));
			if(m.isAnnotationPresent(Paragraphs.class)) {
				Paragraphs p = m.getAnnotation(Paragraphs.class);
				pgm.paraList.add(p.value());
			}
		}
	}
/*
 * 生成变量列表
 */
	public static void WORKING_STORAGE_SECTION(Class<?> clazz, Program pgm) throws Exception {
		Field[] fields = clazz.getDeclaredFields();
		pgm.varTable = new ArrayList<Object>();
		GroupItem currentItem = null;
		GroupItem rootItem = null;
		String itemName = "";

		GroupItem aheadItem = null;
		GroupItem fatherItem = null;
		GroupItem firstBrotherItem = null;
		int aheadLayNo;
		int currentLayNo;

		int pos = 0;
		int curentItemIndex = 0;
		for (Field field : fields) {
			// 创建子项目
			if (field.isAnnotationPresent(PIC.class)) {
				PIC p = (PIC) field.getAnnotation(PIC.class);
				itemName = p.name();
				System.out.println("子项目变量：" + itemName);
				// 仅有1个项目
				currentItem = new ChildItem(Integer.parseInt(p.no()), itemName, p.type(), p.size(), p.value());
				pos = pos + p.size();
				curentItemIndex++;
				//？？occurs合并优化处理
				if (p.OCCURS() > 0) {
					currentItem.setTimes(p.OCCURS());
					//初次创建
					if(currentItem.getTables()==null) {
						currentItem.setTables(new ArrayList<CobolItem>(p.OCCURS()));
					}
					//填入自己到末尾
					currentItem.getTables().add(currentItem.getTables().size(),currentItem);
				}
//				// 存在多个数组变量
//				else {
//					// 创建动态数组
//					ChildItem[] currentItems = new ChildItem[p.OCCURS()];
//					for (int i = 0; i < p.OCCURS(); i++) {
//						currentItem = new ChildItem(Integer.parseInt(p.no()), itemName, p.type(), p.size(), p.value());
//						// 同根
//						currentItem.setRootItem(rootItem);
//						// 同父
//						currentItem.setFatherItem(fatherItem);
//						// 同兄
//						currentItem.setFirstBrotherItem(firstBrotherItem);
//						currentItem.setPos(length);
//
//						currentItems[i] = (ChildItem) currentItem;
//						length = length + p.size();
//
//					}
//					invoke(pgm, "set" + itemName, currentItems);
//					pgm.varTable.add(currentItems);
//				}

			}
			// 创建集团项目
			if (field.isAnnotationPresent(Group.class)) {
				Group g = (Group) field.getAnnotation(Group.class);
				itemName = g.name();
				System.out.println("集团项目变量：" + itemName);
				currentItem = new GroupItem(Integer.parseInt(g.no()), itemName);
				if (g.OCCURS() > 0) {
					currentItem.setTimes(g.OCCURS());
					//初次创建
					if(currentItem.getTables()==null) {
						currentItem.setTables(new ArrayList<CobolItem>(g.OCCURS()));
					}
					//填入自己到末尾
					currentItem.getTables().add(currentItem.getTables().size(),currentItem);
						
				}
			}

			if (itemName != "" && currentItem != null) {
				currentLayNo = currentItem.getLayNo();
				// 01层为根项目
				if (currentLayNo == 1) {
					pos = 0;
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
				//表结构继承
				if(fatherItem!=null) {
				currentItem.setTables(fatherItem.getTables());
				}
				// 同兄
				currentItem.setFirstBrotherItem(firstBrotherItem);
				//occurs项目计算方式需要设计
				currentItem.setPos(pos);
				invoke(pgm, "set" + itemName, currentItem);
				pgm.varTable.add(currentItem);
				curentItemIndex++;
			}
		}
		// 分配空间初始化
		initialization(pgm);
		// 计算长度
		// 计算起始位置
		// 创建命名空间
		makeNameSpace(pgm);
	}

	public static void initialization(Program pgm) {
		List<Object> vars = pgm.varTable;
		if (vars == null || vars.size() == 0)
			return;
		String lay01 = "";
		int length = 0;
		//从变量表后边向前计算
		for (int i = vars.size() - 1; i >= 0; i--) {
			GroupItem currentItem = (GroupItem) pgm.varTable.get(i);
			if(currentItem.getLayNo()>1 && currentItem.getFatherItem() != null) {
				ArrayList<GroupItem> cisOfFather = ((GroupItem)(currentItem.getFatherItem())).getCis();
				if (cisOfFather == null) {cisOfFather = new ArrayList<GroupItem>();}
				cisOfFather.add(currentItem);
			}
			//length 计算， value初期化
			if (currentItem instanceof ChildItem) {
				if (((ChildItem) currentItem).getLayNo() > 1) {
					length = length + currentItem.getLength();
				}
				String sType = ((ChildItem) currentItem).getType();
				String fillStr = " ";
				String value = ((ChildItem) currentItem).getValue();
				if (sType != null) {
					if ("9".equals(sType.substring(0, 1))) {
						fillStr = "0";
						// 9型在前面填充0, 否则在后边填空格
						value = String.join("", Collections.nCopies(currentItem.getLength() - value.length(), fillStr))
								+ value;
					} else {
						value = value + String.join("",
								Collections.nCopies(currentItem.getLength() - value.length(), fillStr));
					}
				}
				// 截取指定长度，从后边往前叠加合并
				lay01 = value + lay01;
			}
			// 拥有父在上子在下
			if ((currentItem instanceof GroupItem) && !(currentItem instanceof ChildItem)) {
				//occurs项目计算方式需要设计
				currentItem.setLength(length);
				if (((GroupItem) currentItem).getLayNo() == 1) {
					currentItem.setByteSpace(new String(lay01));
					lay01 = "";
				}
			}

		}

	}

	public static void makeNameSpace(Program pgm) throws Exception {
		List<Object> vars = pgm.varTable;
		pgm.nameSpace = new HashMap<String, Object>();
		for (Object gi : vars) {
			pgm.nameSpace.put(((GroupItem) gi).getName(), (GroupItem) gi);
		}
	}

	/**
	 * 
	 * @param obj:
	 *            方法执行的那个对象.
	 * @param methodName:
	 *            类的一个方法的方法名. 该方法也可能是私有方法.
	 * @param args:
	 *            调用该方法需要传入的参数
	 * @return: 调用方法后的返回值
	 * 
	 */
	public static Object invoke(Object obj, String methodName, Object... args) throws Exception {
		// 1. 获取 Method 对象
		// 因为getMethod的参数为Class列表类型，所以要把参数args转化为对应的Class类型。

		Class[] parameterTypes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			parameterTypes[i] = args[i].getClass();
			// System.out.println(parameterTypes[i]);
		}

		Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
		// 如果使用getDeclaredMethod，就不能获取父类方法，如果使用getMethod，就不能获取私有方法
		//
		// 2. 执行 Method 方法
		// 3. 返回方法的返回值
		return method.invoke(obj, args);
	}
}
