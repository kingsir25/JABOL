package cobol.lang;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE_USE)
public @interface COPY {
	String value();
	String prefix() default "";
	String suffix() default "";
	String replace_from() default "";
	String replace_to() default "";
}
