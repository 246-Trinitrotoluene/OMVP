package org.diordna.omvp.presenter.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewRequire {

    /**
     * 是否需要view层绑定，默认不需要
     * @return
     */
    boolean isBind() default false;
}
