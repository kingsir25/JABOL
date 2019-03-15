package cobol.pgm;

import static cobol.lang.words.CO.*;

import java.util.ArrayList;

import cobol.pgm.COPY101;
import cobol.copy.COPY102;
import cobol.lang.COPY;
import cobol.lang.CopyBook;
import cobol.lang.Group;
import cobol.lang.words.ChildItem;
import cobol.lang.words.GroupItem;
import cobol.lang.words.OccursGroupItem;
import cobol.lang.PIC;
import cobol.lang.Paragraphs;
import cobol.lang.Program;

import static cobol.copy.COPY101.*;

public class MainPGM4 extends Program {
	// WORKING_STORAGE
	// 01 A03 PIC 9(08)
	// 05 B05 PIC 9(04).
	// 05 C05 PIC 9(02).
	// 05 D05 PIC 9(02).;
	// ??filler??redefine??OCCURS ?? 77层 88 层
	@Group(no = "01", name = "A03")
	GroupItem A03;
	@PIC(no = "05", name = "B05", type = "X", size = 4)
	ChildItem B05;
	@PIC(no = "05", name = "C05", type = "9", size = 2)
	ChildItem C05;
	@PIC(no = "05", name = "D05", type = "X", size = 2)
	ChildItem D05;

	@Group(no = "01", name = "W03")
	GroupItem W03;
	@PIC(no = "05", name = "X05", type = "X", size = 4)
	ChildItem X05;
	@PIC(no = "05", name = "Y05", type = "9", size = 2)
	ChildItem Y05;
	@PIC(no = "05", name = "Z05", type = "X", size = 2)
	ChildItem Z05;
	
	@Group(no = "01", name = "A103")
	GroupItem A103;

	@PIC(no = "05", name = "BB05", type = "X", size = 4, OCCURS = 0)
	ChildItem BB05;

	@COPY("COPY001")
	@Group(no = "01", name = "A203")
	GroupItem A203;

	// 01 WS-TABLE.
	// 05 WS-A OCCURS 10 TIMES.
	// 10 WS-B PIC X(10).
	// 10 WS-C OCCURS 5 TIMES.
	// 15 WS-D PIC X(6).

	// @Group(no="05",name="WS_A",OCCURS = 10)
	// GroupItem WS_A_1;
	WS_A_table WS_A = new WS_A_table(5, "WS_A", 10);

	class WS_A_table extends OccursGroupItem {
		@PIC(no = "10", name = "WS_B", type = "X", size = 10)
		ChildItem WS_B;
		@Group(no = "10", name = "WS_C", OCCURS = 5)
		WS_C_table WS_C = new WS_C_table(5, "WS-C", 5);

		class WS_C_table extends OccursGroupItem {
			@PIC(no = "15", name = "WS _D", type = "X", size = 6)
			ChildItem WS_D = new ChildItem(15, "WS_D", "X", 6, "");

			WS_C_table(int layNo, String name, int ocr) {
				super(layNo, name, ocr);
			}
//			@Override
//			public ArrayList<GroupItem> getGis() {
//				// TODO Auto-generated method stub
//				return super.getGis();
//			}

		}

		WS_A_table(int layNo, String name, int ocr) {
			super(layNo, name, ocr);
		}
	}
	
	class PrefixAA_ extends COPY101{}
	class PrefixAB_ extends COPY102{}
	
	public void PROCEDURE_DIVISION() {
		DISPLAY("PROCEDURE_DIVISION ", " 开始执行");
		// CP_A03 = new GroupItem();
		DISPLAY(A03);
		MOVE("20190110", A03);
		DISPLAY(A03);
		MOVE("02", C05);
		DISPLAY(A03);
		DISPLAY($("C05"));
		MOVE("20190210", W03);
		if(W03.GREATER_THAN(A03)) {
			DISPLAY("W03 > A03");
		}
		for (Object o : varTable) {
			System.out.println("变量:" + ((GroupItem) o).getName());
		}
		for (String s : paraList) {
			System.out.println("段落名:" + s);
		}
	}
//	   PERFORM C-PARA THRU E-PARA.
//	   
//	   B-PARA.
//	   DISPLAY 'IN B-PARA'.
//	   STOP RUN.
	@Paragraphs("B-PARA")
	void B_PARA() {
		DISPLAY("IN B-PARA");
	}   
//	   C-PARA.
//	   DISPLAY 'IN C-PARA'.
	@Paragraphs("C-PARA")
	void C_PARA() {
		DISPLAY("IN C-PARA");
	}   
//	   D-PARA.
//	   DISPLAY 'IN D-PARA'.
	@Paragraphs("D-PARA")
	void D_PARA() {
		DISPLAY("IN D-PARA");
	}	   
//	   E-PARA.
//	   DISPLAY 'IN E-PARA'.
@Paragraphs("E-PARA")
void E_PARA() {
	DISPLAY("IN E-PARA");
}
	/*
	 * 上面代码保留了COBOL语言风格，给COBOL程序员很高的可读性，可维护性 与JAVA程序可以有机结合，完全兼容，提高了扩展性
	 * 下面代码固定，如有增减项目需要修改get与set方法
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("---Start---");
		MainPGM4 pgm = new MainPGM4();
		// IDENTIFICATION DIVISION.
		// PROGRAM-ID. HELLO.
		// ENVIRONMENT DIVISION.
		// INPUT-OUTPUT SECTION.
		// FILE-CONTROL.
		// SELECT FILEN ASSIGN TO INPUT.
		// ORGANIZATION IS SEQUENTIAL.
		// ACCESS IS SEQUENTIAL.
		// DATA DIVISION.
		// FILE SECTION.
		// FD FILEN
		// 01 NAME PIC A(25).
		// WORKING－STORAGE SECTION.
		WORKING_STORAGE_SECTION(MainPGM4.class, pgm);
		COPY(COPY101.create(), pgm);
		makeParaList(MainPGM4.class, pgm);
		// CopyBook cp101 = new CopyBook();
		// COPY(COPY101.class,cp101);
		// LOCAL-STORAGE SECTION.
		// PROCEDURE DIVISION.
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

	GroupItem AA03;

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

	public GroupItem getW03() {
		return W03;
	}

	public void setW03(GroupItem w03) {
		W03 = w03;
	}

	public ChildItem getX05() {
		return X05;
	}

	public void setX05(ChildItem x05) {
		X05 = x05;
	}

	public ChildItem getY05() {
		return Y05;
	}

	public void setY05(ChildItem y05) {
		Y05 = y05;
	}

	public ChildItem getZ05() {
		return Z05;
	}

	public void setZ05(ChildItem z05) {
		Z05 = z05;
	}

	public WS_A_table getWS_A() {
		return WS_A;
	}

	public void setWS_A(WS_A_table wS_A) {
		WS_A = wS_A;
	}
	
}
