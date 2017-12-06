package com.mj.shishicai.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.mj.shishicai.R;
import com.mj.shishicai.activitys.ActivityManhua;
import com.mj.shishicai.activitys.MainActivity;
import com.mj.shishicai.activitys.WXMeiwenActivity;
import com.mj.shishicai.activitys.WeixinActivity;
import com.mj.shishicai.activitys.XiaohuaActivity;
import com.mj.shishicai.adapters.TieziAdapter;
import com.mj.shishicai.databinding.FragmentIndexBinding;
import com.mj.shishicai.iwebview.UIBaseFragment;

/**
 * Created by xinru on 2017/12/3.
 */

public class IndexFragment extends UIBaseFragment<FragmentIndexBinding> implements View.OnClickListener {
    private TieziAdapter adapter;

    public static IndexFragment getInstant() {
        return new IndexFragment();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_index;
    }

    @Override
    protected void init() {
        adapter = new TieziAdapter(getActivity());
        databinding.tiezi.setOnClickListener(this);
        databinding.haoyou.setOnClickListener(this);
        databinding.weixin.setOnClickListener(this);
        databinding.xiaohua.setOnClickListener(this);

        databinding.manhua.setOnClickListener(this);
        databinding.more.setOnClickListener(this);

        databinding.bannerGuide.setData(R.drawable.vip_club, R.drawable.guide_1, R.drawable.thmb8, R.drawable.guide_3);
//        OkGo
//                .<String>post("http://route.showapi.com/44-1")
//                .params("showapi_appid", "51344")
//                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
//                .execute(new StringCallback(){
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        LogUtil.e(response.body());
//                    }
//                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiezi:
                startActivity(new Intent(getContext(), WXMeiwenActivity.class));
                break;
            case R.id.haoyou:
                MainActivity activity = (MainActivity) getActivity();
                activity.changePosition(2);
                break;
            case R.id.weixin:
                startActivity(new Intent(getContext(), WeixinActivity.class));
                break;
            case R.id.xiaohua:
                startActivity(new Intent(getContext(), XiaohuaActivity.class));
                break;
            case R.id.manhua:
                startActivity(new Intent(getContext(), ActivityManhua.class));
                break;
            case R.id.more:
                Toast.makeText(getContext(), "正在努力开发中~~", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
