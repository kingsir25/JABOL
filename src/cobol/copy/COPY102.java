package cobol.copy;


import cobol.lang.CopyBook;
import cobol.lang.Group;

import cobol.lang.PIC;
import cobol.lang.words.ChildItem;
import cobol.lang.words.GroupItem;

public class COPY102  extends CopyBook{
	// 01 AA03 PIC 9(08)
	//	05 BB05 PIC 9(04).
	// 	05  CC05 PIC 9(02).
	// 	05 DD05 PIC 9(02).;
	//??filler??redefine??OCCURS 
		@Group(no="01",name="CP_AA03")
		public  GroupItem  CP_AA03;
		@PIC(no="05",name="CP_BB05",type="X",size=4)
		public  ChildItem  CP_BB05;
		@PIC(no="05",name="CP_CC05",type="9",size=2)
		public  ChildItem  CP_CC05;
		@PIC(no="05",name="CP_DD05",type="X",size=2)
		public  ChildItem  CP_DD05;
		public GroupItem getCP_AA03() {
			return CP_AA03;
		}
		public void setCP_AA03(GroupItem cP_AA03) {
			CP_AA03 = cP_AA03;
		}
		public ChildItem getCP_BB05() {
			return CP_BB05;
		}
		public void setCP_BB05(ChildItem cP_BB05) {
			CP_BB05 = cP_BB05;
		}
		public ChildItem getCP_CC05() {
			return CP_CC05;
		}
		public void setCP_CC05(ChildItem cP_CC05) {
			CP_CC05 = cP_CC05;
		}
		public ChildItem getCP_DD05() {
			return CP_DD05;
		}
		public void setCP_DD05(ChildItem cP_DD05) {
			CP_DD05 = cP_DD05;
		}

}
