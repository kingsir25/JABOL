package cobol.copy;


import cobol.lang.CopyBook;
import cobol.lang.Group;

import cobol.lang.PIC;
import cobol.lang.words.ChildItem;
import cobol.lang.words.GroupItem;

public class COPY101  extends CopyBook{
	// 01 A03 PIC 9(08)
	//	05 B05 PIC 9(04).
	// 	05  C05 PIC 9(02).
	// 	05 D05 PIC 9(02).;
	//??filler??redefine??OCCURS 
		@Group(no="01",name="CP_A03")
		public  GroupItem  CP_A03;
		@PIC(no="05",name="CP_B05",type="X",size=4)
		public  ChildItem  CP_B05;
		@PIC(no="05",name="CP_C05",type="9",size=2)
		public  ChildItem  CP_C05;
		@PIC(no="05",name="CP_D05",type="X",size=2)
		public  ChildItem  CP_D05;

		public  GroupItem getCP_A03() {
			return CP_A03;
		}
		public  void setCP_A03(GroupItem cP_A03) {
			CP_A03 = cP_A03;
		}
		public  ChildItem getCP_B05() {
			return CP_B05;
		}
		public  void setCP_B05(ChildItem cP_B05) {
			CP_B05 = cP_B05;
		}
		public  ChildItem getCP_C05() {
			return CP_C05;
		}
		public  void setCP_C05(ChildItem cP_C05) {
			CP_C05 = cP_C05;
		}
		public  ChildItem getCP_D05() {
			return CP_D05;
		}
		public  void setCP_D05(ChildItem cP_D05) {
			CP_D05 = cP_D05;
		}
}
