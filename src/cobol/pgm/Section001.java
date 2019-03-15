package cobol.pgm;

import static cobol.pgm.WORKAREA.*;
import static cobol.pgm.COPY001.*;
public class Section001 {

	static void $200_GET_CUST_NAME() {
		// TODO Auto-generated method stub
		a03 = "PGM001";
	}
	static void  $100_GET_CUST_NAME() {
		//MOVE '1234' TO B-CO-NO-KIND
		B = "PGM001";
	}
}
