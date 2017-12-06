package com.mj.shishicai.adapters;

import android.content.Context;

import com.mj.shishicai.R;
import com.mj.shishicai.bmobquery.Tiezi;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class TieziAdapter extends BGAAdapterViewAdapter<Tiezi> {

    public TieziAdapter(Context context) {
        super(context, R.layout.item_tiezi);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, Tiezi model) {
        viewHolderHelper.setText(R.id.tv_item_normal_title, model.getTitle()).setText(R.id.tv_item_normal_detail, model.getContent());
    }
}