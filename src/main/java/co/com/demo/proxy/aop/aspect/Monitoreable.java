package co.com.demo.proxy.aop.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
/**
 * Está anotación hace que cualquier clase anotada con la misma se refleje en un log de un proxy
 */
public @interface Monitoreable {
}
