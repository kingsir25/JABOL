package cobol.pgm;

import static cobol.pgm.WORKAREA.*;
import static cobol.pgm.COPY001.*;
public class Section002 {

	static void $300_GET_CUST_NAME() {
		// TODO Auto-generated method stub
		a03 = "PGM002";
	}
	static void  $400_GET_CUST_NAME() {
		//MOVE '1234' TO B-CO-NO-KIND
		B = "PGM002";
	}
}
