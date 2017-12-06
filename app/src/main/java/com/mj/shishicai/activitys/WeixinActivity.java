package com.mj.shishicai.activitys;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mj.shishicai.R;
import com.mj.shishicai.adapters.CaipiaoAdapter;
import com.mj.shishicai.databinding.LayoutListviewBinding;
import com.mj.shishicai.models.CaiPiaoModel;
import com.mj.shishicai.tools.LogUtil;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildLongClickListener;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by xinru on 2017/12/3.
 */

public class WeixinActivity extends UIActivity<LayoutListviewBinding> implements BGARefreshLayout.BGARefreshLayoutDelegate,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, BGAOnItemChildClickListener, BGAOnItemChildLongClickListener {
    private CaipiaoAdapter mAdapter;
    private int page = 1;
    private int pageCount = 0;

    @Override
    protected int getConteneView() {
        return R.layout.layout_listview;
    }

    @Override
    protected void init() {
        tvTitle.setText("开奖了");
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

        mAdapter = new CaipiaoAdapter(context);
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
                .<String>post("http://route.showapi.com/44-1")
                .params("showapi_appid", "51344")
                .params("showapi_sign", "953a234482924251becfef4eafd4a8eb")
                .execute(new StringCallback(){
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.e(response.body());
                        CaiPiaoModel xiaohuaModel = new Gson().fromJson(response.body(), new TypeToken<CaiPiaoModel>(){}.getType());
                        List<CaiPiaoModel.ShowapiResBodyBean.ResultBean> list = xiaohuaModel.getShowapi_res_body().getResult();
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
       return false;
    }
}
