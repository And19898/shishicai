package com.mj.shishicai.activitys;

import android.text.Html;

import com.mj.shishicai.R;
import com.mj.shishicai.databinding.ActivityXiaohuaDetailBinding;
import com.mj.shishicai.models.XiaohuaModel;

/**
 * Created by xinru on 2017/12/3.
 */

public class XiaohuaDetailActivity extends UIActivity<ActivityXiaohuaDetailBinding> {
    @Override
    protected int getConteneView() {
        return R.layout.activity_xiaohua_detail;
    }

    @Override
    protected void init() {
        XiaohuaModel.ShowapiResBodyBean.ContentlistBean model = (XiaohuaModel.ShowapiResBodyBean.ContentlistBean) getIntent().getSerializableExtra("data");
        tvTitle.setText(model.getTitle());
        databinding.tvTitle1.setText(model.getTitle());
        databinding.tvContent.setText(Html.fromHtml(model.getText()));
    }
}
