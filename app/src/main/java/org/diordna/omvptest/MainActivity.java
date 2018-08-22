package org.diordna.omvptest;

import android.os.Bundle;

import org.diordna.omvp.BaseActivity;
import org.diordna.omvptest.presenter.MainPresenter;
import org.diordna.omvptest.view.MainView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainView getView() {
        return new MainView(this);
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void processClick(int resId) {

        if (resId == R.id.test) {
            getRealPresenter().test();
        }
    }
}