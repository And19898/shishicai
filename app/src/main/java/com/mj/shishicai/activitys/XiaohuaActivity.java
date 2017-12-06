package com.mj.shishicai.activitys;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mj.shishicai.R;
import com.mj.shishicai.adapters.XiaohuaAdapter;
import com.mj.shishicai.databinding.LayoutListviewBinding;
import com.mj.shishicai.models.XiaohuaModel;

import java.io.Serializable;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildLongClickListener;

/**
 * Created by xinru on 2017/12/3.
 */

public class XiaohuaActivity extends UIActivity<LayoutListviewBinding> implements BGARefreshLayout.BGARefreshLayoutDelegate,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, BGAOnItemChildClickListener, BGAOnItemChildLongClickListener {
    private XiaohuaAdapter mAdapter;
    private int page = 1;
    private int pageCount = 0;

    @Override
    protected int getConteneView() {
        return R.layout.layout_listview;
    }

    @Override
    protected void init() {
        tvTitle.setText("每日一笑");
        databinding.rlListviewRefresh.setDelegate(this);
        databinding.lvListviewData.setOnItemClickListener(this);
        databinding.lvListviewData.setOnItemLongClickListener(this);
        databinding.lvListviewData.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                Log.i(TAG, "滚动状态变化");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.i(TAG, "正在滚动");
            }
        });

        mAdapter = new XiaohuaAdapter(context);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);






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

    private void request(final boolean isRefresh){
        OkGo
                .<String>post("http://route.showapi.com/341-1")
                .params("showapi_appid", "51344")
                .params("page", page+"")
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new StringCallback(){
                    @Override
                    public void onSuccess(Response<String> response) {
                        XiaohuaModel xiaohuaModel = new Gson().fromJson(response.body(), new TypeToken<XiaohuaModel>(){}.getType());
                        pageCount = xiaohuaModel.getShowapi_res_body().getAllPages();
                        List<XiaohuaModel.ShowapiResBodyBean.ContentlistBean> list = xiaohuaModel.getShowapi_res_body().getContentlist();
                        if(isRefresh){
                            mAdapter.addNewData(list);
                        }else{
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
        Intent intent = new Intent(context, XiaohuaDetailActivity.class);
        intent.putExtra("data", (Serializable) mAdapter.getData().get(position));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {

    }

    @Override
    public boolean onItemChildLongClick(ViewGroup parent, View childView, int position) {
        return false;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        page =1 ;
        request(true);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        page ++;
        if (page > pageCount) {
            databinding.rlListviewRefresh.endLoadingMore();
            Toast.makeText(context, "没有最新数据了", Toast.LENGTH_LONG).show();
            return false;
        }
        request(false);
        return true;
    }
}
