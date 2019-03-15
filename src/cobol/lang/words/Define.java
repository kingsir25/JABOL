package cobol.lang.words;
public class Define {
	// 05 D05 PIC 9(02).
	public static ChildItem PIC(int layNo,String name,String type,int size,String value) {
		ChildItem ci=null;
		ci = new ChildItem(layNo, name, type, size, value);
		return ci;
	}
	public static GroupItem COPY(int layNo,String name) {
		GroupItem gi=null;
		gi =  new GroupItem(layNo, name);
		return gi;
	}
}

