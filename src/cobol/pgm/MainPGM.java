package cobol.pgm;
import static cobol.pgm.WORKAREA.*;
import static cobol.pgm.COPY001.*;
import static cobol.pgm.Section001.*;
import static cobol.pgm.Section002.*;
import  cobol.lang.*;
public class MainPGM {
	public void WORKING_STORAGE(){
		COPY101 wk = new COPY101();
		C0PY102 wk1 = new C0PY102();
		System.out.println(wk1.A03.toString());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("before:"+a03);
		$200_GET_CUST_NAME();		
		System.out.println("after:"+a03);
		$300_GET_CUST_NAME();	
		System.out.println("after:"+a03);
		$100_GET_CUST_NAME();		
		System.out.println("after:"+B);

        //MOVE();		
	}

}
