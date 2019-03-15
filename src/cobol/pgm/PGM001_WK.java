package cobol.pgm;


import cobol.lang.PIC;
import cobol.lang.words.ChildItem;
import cobol.lang.words.GroupItem;

public class PGM001_WK {
	@PIC(no="03",type="X",name="A1",size=8)
	GroupItem  A03;
	@PIC(no="05",type="X",name="B1",size=4)
	ChildItem  B05;
	@PIC(no="05",type="X",name="C1",size=2)
	ChildItem  C05;
	@PIC(no="05",type="X",name="D1",size=2)
	ChildItem  D05;
}
