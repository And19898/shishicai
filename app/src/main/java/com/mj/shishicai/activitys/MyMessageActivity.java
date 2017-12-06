package com.mj.shishicai.activitys;

import com.mj.shishicai.R;
import com.mj.shishicai.databinding.ActivityMyMessageBinding;
import com.mj.shishicai.tools.Cons;
import com.mj.shishicai.tools.PrefreshHelper;

/**
 * author: Rea.X
 * date: 2017/12/4.
 */

public class MyMessageActivity extends UIActivity<ActivityMyMessageBinding> {

    @Override
    protected int getConteneView() {
        return R.layout.activity_my_message;
    }

    @Override
    protected void init() {
        tvTitle.setText("我的资料");
        databinding.tvUsername.setText(PrefreshHelper.get(Cons.USERNAME));
    }
}
