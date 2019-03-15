package cobol.pgm;
import static cobol.lang.words.CO.*;
public class MainPGM2 {
	//定义WORKING_STORAGE 类 子类
	public void WORKING_STORAGE(){
		C0PY102 wk1 = new C0PY102();
		System.out.println(wk1.A03.toString());
		
		System.out.println(wk1.D05.toString());
		MOVE(wk1.C05,wk1.D05);	
		System.out.println(wk1.D05.toString());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainPGM2 $ = new  MainPGM2();
		System.out.println("Start");
		$.WORKING_STORAGE();
			
	}

}
