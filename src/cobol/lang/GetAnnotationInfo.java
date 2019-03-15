package cobol.lang;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cobol.copy.COPY101;

public class GetAnnotationInfo {
	    public static void getFruitInfo(Class<?> clazz){
	        Field[] fields = clazz.getDeclaredFields();
	        for(Field field :fields){
	            if(field.isAnnotationPresent(PIC.class)){
	                PIC p = (PIC) field.getAnnotation(PIC.class);
	                String pname ="变量名称："+p.name();
	                System.out.println(pname);
	            }
	        }
	    }
	    public static void test1(){
	    	GetAnnotationInfo.getFruitInfo(COPY101.class);
	    }
	    public static void main(String[] args) {
	    	test1();
	    }
	}

