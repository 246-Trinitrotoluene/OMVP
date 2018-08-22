package org.diordna.omvptest.view;

import android.content.Context;

import org.diordna.omvp.view.BaseView;
import org.diordna.omvptest.R;

public class MainView extends BaseView {

    public MainView(Context context) {
        super(context);

        tools.setClick(R.id.test);
    }
}
