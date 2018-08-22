package org.diordna.omvp;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.diordna.omvp.presenter.BasePresenter;
import org.diordna.omvp.view.BaseView;

/**
 * Activity的框架基类
 * @param <V>
 * @param <P>
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener {

    private P presenter;

    /**
     * 获取Presenter对象
     * @return
     */
    protected P getRealPresenter() {

        if (presenter == null) {
            presenter = getPresenter();
        }

        if (!presenter.isBind()) {
            presenter.installView(getView());
        }

        return presenter;
    }

    @Override
    public void onClick(View view) {

        processClick(view.getId());
    }

    /**
     * 初始化view
     * @return
     */
    protected abstract V getView();

    /**
     * 初始化Presenter
     * @return
     */
    protected abstract P getPresenter();

    /**
     * 处理点击事件
     * @param resId
     */
    protected abstract void processClick(int resId);
}
