package net.redstone233.tmb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记Fabric平台的代码
 * 仅用于运行时判断，不做具体作用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface Fabric {
    Platform type() default Platform.FABRIC;
    boolean value() default true;
}