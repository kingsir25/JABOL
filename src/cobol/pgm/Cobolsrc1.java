package cobol.pgm;

import cobol.pgm.OuterClass.InnerClass;

 class Cobolsrc1 {
	//WORKING-STORAGE SECTION.
	 static class WORKING_STORAGE{
		//05 REPORT-START
		 static class REPORT_AREA{
			 static class REPORT_START{
				
			}
		}
		//01 WORK-AREA.
		 static class WORK_AREA{
			//05 B-CO-NO                 PIC 9(01).
			 static long B_CO_NO;
			//05 B-CO-NO-KIND            PIC X(04).
			 static String B_CO_NO_KIND;
		}
	}
	
	public static void main(String[] args) {
		CobolBean.b_yymmdd ="20180101";
		CobolBean cobolBean = new CobolBean();
		CobolBean.B_YYMMDD_R  a = cobolBean.new B_YYMMDD_R();
		System.out.println(a);
		OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.display();
		
		// TODO Auto-generated method stub
		//DISPLAY "資料處理中......"
		System.out.println("資料處理中......");
		//PERFORM 100_GET_CUST_NAM
		//Cobolsrc1 Cobolsrc1 = new Cobolsrc1();
		$100_GET_CUST_NAME();
		System.out.println(WORKING_STORAGE.WORK_AREA.B_CO_NO_KIND);
		WORKING_STORAGE.WORK_AREA.B_CO_NO_KIND = "5678";
		
	}
	//100-GET-CUST-NAME.
	static void  $100_GET_CUST_NAME() {
		//MOVE '1234' TO B-CO-NO-KIND
		WORKING_STORAGE.WORK_AREA.B_CO_NO_KIND = "1234";
	}

}
