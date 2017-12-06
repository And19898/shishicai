package com.mj.shishicai.activitys;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.mj.shishicai.R;
import com.mj.shishicai.bmobquery.Tiezi;
import com.mj.shishicai.databinding.ActivityAddTieziBinding;
import com.mj.shishicai.tools.Cons;
import com.mj.shishicai.tools.PrefreshHelper;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * author: Rea.X
 * date: 2017/12/4.
 */

public class AddTieziActivity extends UIActivity<ActivityAddTieziBinding> {
    @Override
    protected int getConteneView() {
        return R.layout.activity_add_tiezi;
    }

    @Override
    protected void init() {
        databinding.tvAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = databinding.tvAddTitle.getText().toString().trim();
                String content = databinding.tvAddContnet.getText().toString().trim();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    Toast.makeText(context, "标题内容不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                send(title, content);
            }
        });
    }

    private void send(String title, String content) {
        dialog.show();
        Tiezi tiezi = new Tiezi();
        tiezi.setTitle(title);
        tiezi.setContent(content);
        tiezi.setUsername(PrefreshHelper.get(Cons.USERNAME));
        tiezi.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                dialog.dismiss();
                if (e == null) {
                    Toast.makeText(context, "发布成功", Toast.LENGTH_LONG).show();
                    finish();
                    return;
                }
                Toast.makeText(context, "发布失败，请稍后再试", Toast.LENGTH_LONG).show();
            }
        });
    }
}
