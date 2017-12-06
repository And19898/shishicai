package com.mj.shishicai.adapters;

import android.content.Context;

import com.mj.shishicai.R;
import com.mj.shishicai.models.CaiPiaoModel;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class CaipiaoAdapter extends BGAAdapterViewAdapter<CaiPiaoModel.ShowapiResBodyBean.ResultBean> {

    public CaipiaoAdapter(Context context) {
        super(context, R.layout.item_caipiao);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position,CaiPiaoModel.ShowapiResBodyBean.ResultBean model) {
        viewHolderHelper
                .setText(R.id.tv_item_normal_title, model.getName())
                .setText(R.id.tv_item_normal_qishu, "期数："+model.getExpect())
                .setText(R.id.tv_item_normal_time, model.getTime())
                .setText(R.id.tv_item_normal_detail, model.getOpenCode());
    }
}