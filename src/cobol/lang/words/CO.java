package cobol.lang.words;

import cobol.lang.CopyBook;
import cobol.lang.Program;

public class CO {
	public static void MOVE(CobolItem from, CobolItem to) {
		//修改成接口，实现value 空间传递
		to.setByteSpace(from.getByteSpace());
	}
	
	public static void MOVE(String from, CobolItem to) {
		//修改成接口，实现value 空间传递
		to.setByteSpace(from);
	}
	public static void DISPLAY(String ... strs) {
		//修改成接口，实现value 空间传递
		String rs ="";
		for(String s:strs) {
			rs =rs + s;
		}
		System.out.println(rs);
	}	
	public static void DISPLAY(CobolItem ci) {
		//修改成接口，实现value 空间传递
		System.out.println(new String(ci.getByteSpace()));;
	}
	public static void COPY(Class<?> clazz,CopyBook cp) {
		//WORKING_STORAGE 导入对象变量
		;
	}
	public static void COPY(CopyBook cb, Program pgm) {
		//WORKING_STORAGE 导入对象变量
		pgm.mergeVT(cb.getVarTable());
	}
	public static void load (CobolItem from) {
		//WORKING_STORAGE 导入对象变量
		;
	}

}
