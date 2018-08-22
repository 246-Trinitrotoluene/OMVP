package org.diordna.omvp.presenter;

import org.diordna.omvp.presenter.chain.Chain;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Presenter代理，过滤未达到指定条件的方法
 * @param <P>
 */
public class PresenterProxy<P extends BasePresenter> implements InvocationHandler {

    private P p;

    public P getInstance(P p) {
        this.p = p;
        Class clazz = p.getClass();
        P obj = (P) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                clazz.getInterfaces(),
                this);
        return obj;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Annotation[] annos = method.getDeclaredAnnotations();
        for (Annotation anno : annos) {
            for (int i = 0; i < p.chains.size(); i++) {
                Chain chain = (Chain) p.chains.get(i);
                if (!chain.enable(p, anno)) {
                    return null;
                }
            }
        }

        method.invoke(p, objects);
        return null;
    }
}