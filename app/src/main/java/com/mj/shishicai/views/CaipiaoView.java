package com.mj.shishicai.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by xinru on 2017/12/3.
 */

public class CaipiaoView extends LinearLayout{
    public CaipiaoView(Context context) {
        super(context);
    }

    public CaipiaoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CaipiaoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CaipiaoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
