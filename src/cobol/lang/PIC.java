package cobol.lang;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface PIC {
	String no() default  "01";
	String name();
	String type() default "X";
	int size() default 1;	
	String value() default "";
	int OCCURS() default 0;
}
