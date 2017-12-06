package com.mj.shishicai.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mj.shishicai.R;
import com.mj.shishicai.adapters.ManhuaAdapter;
import com.mj.shishicai.databinding.LayoutListviewBinding;
import com.mj.shishicai.models.ManhuaModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * author: Rea.x
 * date: 2017/12/5.
 */

public class ActivityManhua extends UIActivity<LayoutListviewBinding> implements BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener {
    private ManhuaAdapter mAdapter;
    private int page = 1;
    private boolean hasMore = true;

    private static String type1 = "/category/weimanhua/kbmh";
    private static String type2 = "/category/weimanhua/gushimanhua";
    private static String type3 = "/category/duanzishou";
    private static String type4 = "/category/lengzhishi";
    private static String type5 = "/category/qiqu";
    private static String type6 = "/category/dianying";
    private static String type7 = "/category/gaoxiao";
    private static String type8 = "/category/mengchong";
    private static String type9 = "/category/xinqi";
    private static String type10 = "/category/huanqiu";
    private static String type11 = "/category/sheying";
    private static String type12 = "/category/wanyi";
    private static String type13 = "/category/chahua";

    private List<String> types;

    private String selectType;

    @Override
    protected int getConteneView() {
        return R.layout.layout_listview;
    }

    @Override
    protected void init() {
        getType();
        tvTitle.setText("漫画");
        databinding.rlListviewRefresh.setDelegate(this);
        databinding.lvListviewData.setOnItemClickListener(this);
        mAdapter = new ManhuaAdapter(context);


        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(context, true);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.custom_mooc_icon);
//        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setSpringDistanceScale(0.2f);
//        moocStyleRefreshViewHolder.setRefreshViewBackgroundColorRes(R.color.custom_imoocstyle);
        databinding.rlListviewRefresh.setRefreshViewHolder(moocStyleRefreshViewHolder);
        databinding.lvListviewData.setAdapter(mAdapter);
        databinding.rlListviewRefresh.beginRefreshing();
        request(true);
    }

    private void getType() {
        types = new ArrayList<>();
        types.add(type1);
        types.add(type2);
        types.add(type3);
        types.add(type4);
        types.add(type5);
        types.add(type6);
        types.add(type7);
        types.add(type8);
        types.add(type9);
        types.add(type10);
        types.add(type11);
        types.add(type12);
        types.add(type13);
        selectType = types.get((int) (Math.random() * types.size()));
    }

    private void request(final boolean isRefresh) {
        OkGo
                .<String>post("http://route.showapi.com/958-1")
                .params("showapi_appid", "51344")
                .params("page", page + "")
                .params("type", selectType)
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ManhuaModel xiaohuaModel = new Gson().fromJson(response.body(), new TypeToken<ManhuaModel>() {
                        }.getType());
                        hasMore = xiaohuaModel.getShowapi_res_body().getPagebean().isHasMorePage();
                        List<ManhuaModel.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = xiaohuaModel.getShowapi_res_body().getPagebean().getContentlist();
                        if (isRefresh) {
                            mAdapter.addNewData(list);
                        } else {
                            mAdapter.addMoreData(list);
                        }
                        databinding.rlListviewRefresh.endRefreshing();
                        databinding.rlListviewRefresh.endLoadingMore();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        databinding.rlListviewRefresh.endRefreshing();
                        databinding.rlListviewRefresh.endLoadingMore();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, ManhuaDetailActivity.class);
        intent.putExtra("data", (Serializable) mAdapter.getData().get(position));
        startActivity(intent);
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        page = 1;
        request(true);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        page++;
        if (hasMore) {
            databinding.rlListviewRefresh.endLoadingMore();
            Toast.makeText(context, "没有最新数据了", Toast.LENGTH_LONG).show();
            return false;
        }
        request(false);
        return true;
    }
}
