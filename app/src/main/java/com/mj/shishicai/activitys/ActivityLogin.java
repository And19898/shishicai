package com.mj.shishicai.activitys;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.Toast;

import com.mj.shishicai.R;
import com.mj.shishicai.bmobquery.User;
import com.mj.shishicai.databinding.ActivityLoginBinding;
import com.mj.shishicai.tools.PrefreshHelper;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;

import static com.mj.shishicai.tools.Cons.USERNAME;

/**
 * Created by xinru on 2017/12/3.
 */

public class ActivityLogin extends UIActivity<ActivityLoginBinding> implements DefaultLoginView.DefaultLoginViewListener, DefaultRegisterView.DefaultRegisterViewListener {
    @Override
    protected int getConteneView() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        tvTitle.setText("登陆注册");
        DefaultLoginView loginView = (DefaultLoginView) databinding.loginview.getLoginView();
        DefaultRegisterView registerView = (DefaultRegisterView) databinding.loginview.getRegisterView();
        loginView.setListener(this);
        registerView.setListener(this);
    }

    @Override
    public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
        dialog.show();
        final String username = loginUser.getEditText().getText().toString().trim();
        String pwd = loginPass.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(context, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.addWhereEqualTo("pwd", pwd);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                dialog.dismiss();
                if(e == null){
                    if(list != null && list.size() != 0){
                        Toast.makeText(context, "登录成功", Toast.LENGTH_LONG).show();
                        PrefreshHelper.save(USERNAME, username);
                        finish();
                        return;
                    }
                }
                Toast.makeText(context, "用户名或密码错误，请重试！", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
        dialog.show();
        final String username = registerUser.getEditText().getText().toString().trim();
        String pwd = registerPass.getEditText().getText().toString().trim();
        String repwd = registerPassRep.getEditText().getText().toString().trim();
        if (!pwd.equalsIgnoreCase(repwd)) {
            Toast.makeText(context, "两次密码输入不一致", Toast.LENGTH_LONG).show();
            return;
        }
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
            User user = new User();
            user.setUsername(username);
            user.setPwd(pwd);
            user.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    dialog.dismiss();
                    if(e != null){
                        Toast.makeText(context, "注册失败，请稍后再试", Toast.LENGTH_LONG).show();
                        return;
                    }
                    PrefreshHelper.save(USERNAME, username);
                    Toast.makeText(context, "注册成功", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }
    }
}
