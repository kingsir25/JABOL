package cobol.pgm;

import cobol.lang.words.ChildItem;
import cobol.lang.words.CobolItem;
import cobol.lang.words.GroupItem;



public class C0PY102 {
	// 01 WORK-AREA.
	CobolItem WORK_AREA = new CobolItem(new  GroupItem(1, "WORK-AREA"));
	// 03 A03.
	CobolItem A03 = new CobolItem(new  GroupItem(3, "A03"));
		// 05 B05 PIC 9(04).
	CobolItem B05 = new CobolItem(new  ChildItem(3, "B05","9",4,"0"));
		// 05  C05 PIC 9(02).
	CobolItem C05 = new CobolItem(new  ChildItem(3, "C05","9",2,"88"));
		// 05 D05 PIC 9(02).
	CobolItem D05 = new CobolItem(new  ChildItem(3, "D05","9",2,"99"));

}
