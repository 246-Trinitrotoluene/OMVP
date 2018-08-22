package org.diordna.omvp.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * activity视图的操作工具类
 * @author DiorDNA
 */
public class ViewTools {

    private WeakReference<Context> context;

    private ViewTools(Context context) {
        this.context = new WeakReference<>(context);
    }

    /**
     * 创建工具类的一个对象
     * @param context activity's context
     * @return ViewTools
     */
    public static ViewTools newInstance(Context context) {

        ViewTools tools = new ViewTools(context);
        return tools;
    }

    /**
     * 根据指定的id获取控件的对象
     * @param resId
     * @param <V>
     * @return
     */
    public <V extends View> V findView(int resId) {

        Activity activity = getActivity();
        if (activity != null) {
            return activity.findViewById(resId);
        }

        return null;
    }

    /**
     * 为控件添加点击事件
     * @param resIds
     */
    public void setClick(int... resIds) {

        if (!clickable()) {
            return;
        }

        for (int resId : resIds) {
            View view = findView(resId);
            view.setOnClickListener((View.OnClickListener) context.get());
        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        Activity activity = getActivity();
        if (activity != null) {

            /**
             * 获取状态栏id
             */
            String attrName = "status_bar_height";
            String attrType = "dimen";
            String attrPackage = "android";
            int resId = activity.getResources().getIdentifier(
                    attrName, attrType, attrPackage
            );

            if (resId > 0) {
                return activity.getResources()
                        .getDimensionPixelSize(resId);
            }
        }

        return 0;
    }

    /**
     * 隐藏状态栏
     */
    public void hideStatusBar() {

        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Window window = activity.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            hideLollipopStatusBar(window);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            hideKitkatStatusBar(window);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            hideJellyBeanStatusBar(window);
        }

        hideStatusBarFinally(activity);
    }

    /**
     * this function is to hide status bar on LOLLIPOP or above
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void hideLollipopStatusBar(Window window) {

        View decoreView = window.getDecorView();
        int option =
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decoreView.setSystemUiVisibility(option);
        window.setNavigationBarColor(Color.TRANSPARENT);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * this function is to hide statusbar on KITKAT or above
     * and below LOLLIPOP
     * @param window
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void hideKitkatStatusBar(Window window) {

        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    /**
     * this function is to hide statusbar on JELLY_BEAN or above
     * and below KITKAT
     * @param window
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void hideJellyBeanStatusBar(Window window) {

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = window.getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * this function is to hide statusbar with last effort
     * @param activity
     */
    private void hideStatusBarFinally(Activity activity) {

        ActionBar bar = activity.getActionBar();
        if (bar != null) {
            bar.hide();
        }
    }

    /**
     * this function is to judge whether context is exist or not
     * @return
     */
    public boolean isExist() {

        if (context != null && context.get() != null) {
            return true;
        }

        return false;
    }

    /**
     * this function is to get activity's object
     * @return
     */
    private Activity getActivity() {

        Activity activity = null;

        if (isExist()) {
            Context context = this.context.get();
            if (context != null) {

                activity = (Activity) context;
            }
        }

        return activity;
    }

    /**
     * this function is to get context with safe method
     * @return
     */
    public Context getContext() {

        if (context != null) {

            return context.get();
        }

        return null;
    }

    /**
     * this function is to get application's context with
     * safe method
     * @return
     */
    public Context getApplicationContext() {

        Context context = getContext();
        if (context != null) {
            return context.getApplicationContext();
        }

        return null;
    }

    /**
     * this function is to check whether activity support click
     * @return
     */
    private boolean clickable() {

        if (isExist() && context.get() instanceof View.OnClickListener) {
            return true;
        }

        return false;
    }
}
