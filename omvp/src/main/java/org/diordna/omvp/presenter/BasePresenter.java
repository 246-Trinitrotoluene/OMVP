package org.diordna.omvp.presenter;

import org.diordna.omvp.presenter.chain.Chain;
import org.diordna.omvp.presenter.chain.ViewChain;
import org.diordna.omvp.view.BaseView;

import java.util.ArrayList;
import java.util.List;

/**
 * Presenter层基类
 * @param <V>
 */
public abstract class BasePresenter<V extends BaseView> {

    private V view;
    protected List<Chain> chains;

    public BasePresenter() {
        chains = new ArrayList<>();
        chains.add(new ViewChain());
    }

    /**
     * Presenter层方法预处理方法链
     * @param chain
     */
    public void addChain(Chain chain) {
        this.chains.add(chain);
    }

    /**
     * 加载view层
     * @param v
     */
    public void installView(V v) {
        this.view = v;
    }

    /**
     * 判断Activity层是否已销毁或者未绑定
     * @return
     */
    public boolean isBind() {
        if (view == null) {
            return false;
        }

        return view.isBind();
    }
}
