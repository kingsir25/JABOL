package cobol.pgm;
import static cobol.lang.words.CO.*;

import cobol.pgm.COPY101;
import cobol.lang.COPY;
import cobol.lang.CopyBook;
import cobol.lang.Group;
import cobol.lang.words.ChildItem;
import cobol.lang.words.GroupItem;
import cobol.lang.PIC;
import cobol.lang.Program;

import static cobol.copy.COPY101.*;

public class MainPGM3 extends Program {
	//WORKING_STORAGE
	// 01 A03 PIC 9(08)
	//	05 B05 PIC 9(04).
	// 	05  C05 PIC 9(02).
	// 	05 D05 PIC 9(02).;
	//??filler??redefine??OCCURS ?? 77层 88 层
		@Group(no="01",name="A03")
		GroupItem  A03;
		@PIC(no="05",name="B05",type="X",size=4)
		ChildItem  B05;
		@PIC(no="05",name="C05",type="9",size=2)
		ChildItem  C05;
		@PIC(no="05",name="D05",type="X",size=2)
		ChildItem  D05;
		
		@Group(no="01",name="A103")
		GroupItem A103;


		@PIC(no="05",name="BB05", type="X", size=4,OCCURS = 0)
		ChildItem  BB05;
		
		@COPY("COPY001")
		@Group(no="01",name="A203")
		GroupItem A203;
		
public  void PROCEDURE_DIVISION() {
	DISPLAY("PROCEDURE_DIVISION ", " 开始执行");
	//CP_A03 = new GroupItem();
	DISPLAY(A03);	
	MOVE("20190110",A03);
	DISPLAY(A03);	
	MOVE("02",C05);
	DISPLAY(A03);
	DISPLAY($("C05"));
}
/*
 * 上面代码保留了COBOL语言风格，给COBOL程序员很高的可读性，可维护性
 * 与JAVA程序可以有机结合，完全兼容，提高了扩展性
 * 下面代码固定，如有增减项目需要修改get与set方法
 */
public static void main(String[] args) throws Exception {
		 System.out.println("---Start---");
         MainPGM3 pgm = new  MainPGM3();
//		IDENTIFICATION DIVISION.
//		PROGRAM-ID. HELLO.
//		ENVIRONMENT DIVISION.
//		INPUT-OUTPUT SECTION.
//		FILE-CONTROL.
//		SELECT FILEN ASSIGN TO INPUT.
//		       ORGANIZATION IS SEQUENTIAL.
//		       ACCESS IS SEQUENTIAL.
//		DATA DIVISION.
//		FILE SECTION.
//		FD FILEN
//		01 NAME PIC A(25).
//        WORKING－STORAGE SECTION.
		   WORKING_STORAGE_SECTION(MainPGM3.class,pgm);
		   COPY(COPY101.create(),pgm);
		   //CopyBook cp101 = new CopyBook();
		   ///COPY(COPY101.class,cp101);
//       LOCAL-STORAGE SECTION.
//       PROCEDURE DIVISION.
            pgm.PROCEDURE_DIVISION();
            
   		 	System.out.println("---end---");
	}

	public GroupItem getA03() {
		return A03;
	}

	public void setA03(GroupItem a03) {
		A03 = a03;
	}

	public ChildItem getB05() {
		return B05;
	}

	public void setB05(ChildItem b05) {
		B05 = b05;
	}

	public ChildItem getC05() {
		return C05;
	}

	public void setC05(ChildItem c05) {
		C05 = c05;
	}

	public ChildItem getD05() {
		return D05;
	}

	public void setD05(ChildItem d05) {
		D05 = d05;
	}
	
	GroupItem  AA03;
	public GroupItem getAA03() {
		return AA03;
	}
	public void setAA03(GroupItem aA03) {
		AA03 = aA03;
	}
	public ChildItem getBB05() {
		return BB05;
	}
	public void setBB05(ChildItem bB05) {
		BB05 = bB05;
	}

	public GroupItem getA103() {
		return A103;
	}
	public void setA103(GroupItem a103) {
		A103 = a103;
	}
	public GroupItem getA203() {
		return A203;
	}
	public void setA203(GroupItem a203) {
		A203 = a203;
	}
}
