package com.mj.shishicai.adapters;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mj.shishicai.R;
import com.mj.shishicai.models.WXMeiwen;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class WeixinmeiwenAdapter extends BGAAdapterViewAdapter<WXMeiwen.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {

    private Activity activity;
    public WeixinmeiwenAdapter(Context context) {
        super(context, R.layout.item_weixin);
        this.activity = (Activity) context;
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, WXMeiwen.ShowapiResBodyBean.PagebeanBean.ContentlistBean model) {
        viewHolderHelper.setText(R.id.tv_item_normal_title, model.getTitle()).setText(R.id.tv_item_normal_detail, model.getUserName() + "  " + model.getDate());
        ImageView imageView = viewHolderHelper.getImageView(R.id.img_head);
        Glide.with(activity).load(model.getContentImg()).into(imageView);
    }
}