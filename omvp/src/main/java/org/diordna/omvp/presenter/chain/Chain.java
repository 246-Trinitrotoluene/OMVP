package org.diordna.omvp.presenter.chain;

import android.content.Context;

import org.diordna.omvp.presenter.BasePresenter;

import java.lang.annotation.Annotation;

public interface Chain {

    /**
     * 判断方法是否允许执行
     * @param presenter
     * @param anno
     * @return
     */
    boolean enable(BasePresenter presenter, Annotation anno);

    /**
     * 方法中断执行时运行
     * @param context
     */
    void failMsg(Context context);
}