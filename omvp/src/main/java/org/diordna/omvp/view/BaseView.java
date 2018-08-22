package org.diordna.omvp.view;

import android.content.Context;

/**
 * 框架view层的基类
 */
public abstract class BaseView {

    protected ViewTools tools;

    public BaseView(Context context) {

        tools = ViewTools.newInstance(context);
    }

    /**
     * 判断是否和activity绑定或者activity是否已销毁
     * @return
     */
    public boolean isBind() {

        if (tools.isExist()) {
            return true;
        }

        return false;
    }
}
