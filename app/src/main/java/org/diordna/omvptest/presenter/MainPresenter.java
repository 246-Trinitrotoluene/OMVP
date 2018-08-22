package org.diordna.omvptest.presenter;

import android.util.Log;

import org.diordna.omvp.presenter.BasePresenter;
import org.diordna.omvp.presenter.anno.MVPRequire;
import org.diordna.omvptest.view.MainView;

public class MainPresenter extends BasePresenter<MainView> {

    @MVPRequire(isBind = true)
    public void test() {
        Log.i("test", "test is clicked");
    }
}
