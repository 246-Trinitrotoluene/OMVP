package org.diordna.omvp.presenter.chain;

import android.content.Context;

import org.diordna.omvp.presenter.BasePresenter;
import org.diordna.omvp.presenter.anno.ViewRequire;

import java.lang.annotation.Annotation;

public class ViewChain implements Chain {
    @Override
    public boolean enable(BasePresenter presenter, Annotation anno) {

        if (anno instanceof ViewRequire) {
            ViewRequire require = (ViewRequire) anno;
            if (require.isBind() && !presenter.isBind()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void failMsg(Context context) {

    }
}
