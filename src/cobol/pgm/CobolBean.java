package cobol.pgm;

import java.text.DecimalFormat;

public class CobolBean {

	// 01 WORK-AREA.
//	static class wk {
		// 05 B-YYMMDD PIC 9(08)
		public static String b_yymmdd;

		// 05 B-YYMMDD-R REDEFINES B-YYMMDD.
		 class B_YYMMDD_R {
			B_YYMMDD_R() {
				b_yy = Integer.parseInt(b_yymmdd.substring(0, 0 + 4));
				b_mm = Integer.parseInt(b_yymmdd.substring(4, 4 + 2));
				b_dd = Integer.parseInt(b_yymmdd.substring(6, 6 + 2));
			}
		
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				 DecimalFormat df9PIC4 = new DecimalFormat("0000");
				 DecimalFormat df9PIC2 = new DecimalFormat("00");
				return df9PIC4.format(b_yy)+df9PIC2.format(b_mm)+df9PIC2.format(b_dd);
			}
		}
			// 10 B-YY PIC 9(04).
			static int b_yy;
			// 10 B-MM PIC 9(02).
			static int b_mm;
			// 10 B-DD PIC 9(02).
			static int b_dd;

}
