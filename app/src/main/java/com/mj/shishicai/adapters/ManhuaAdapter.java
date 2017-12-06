package com.mj.shishicai.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.mj.shishicai.R;
import com.mj.shishicai.models.ManhuaModel;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

public class ManhuaAdapter extends BGAAdapterViewAdapter<ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> {

    private Context context;
    public ManhuaAdapter(Context context) {
        super(context, R.layout.item_manhua);
        this.context = context;
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper viewHolderHelper) {
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean model) {
        viewHolderHelper.setText(R.id.tv_item_normal_title, model.getTitle());
        LinearLayout layout = viewHolderHelper.getView(R.id.img_layout);
        ImageView img1 = viewHolderHelper.getImageView(R.id.img_1);
        ImageView img2 = viewHolderHelper.getImageView(R.id.img_2);
        ImageView img3 = viewHolderHelper.getImageView(R.id.img_3);
        ImageView img4 = viewHolderHelper.getImageView(R.id.img_4);
        List<String> pics = model.getThumbnailList();
        if(pics == null || pics.size() == 0){
            layout.setVisibility(View.GONE);
        }else{
            layout.setVisibility(View.VISIBLE);
        }

        if(pics.size() == 1){
            img1.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.VISIBLE);
            Glide.with(context).load(pics.get(0)).into(img4);
        }
        if(pics.size() == 2){
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            img3.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.INVISIBLE);
            Glide.with(context).load(pics.get(0)).into(img1);
            Glide.with(context).load(pics.get(1)).into(img1);
        }

        if(pics.size() >= 3){
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
            img3.setVisibility(View.VISIBLE);
            img4.setVisibility(View.INVISIBLE);
            Glide.with(context).load(pics.get(0)).into(img1);
            Glide.with(context).load(pics.get(1)).into(img2);
            Glide.with(context).load(pics.get(2)).into(img3);
        }
    }
}