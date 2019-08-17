package com.example.zhuangguang.module_user.ui.account;

import android.text.TextUtils;
import android.widget.EditText;

import com.example.zhuangguang.common.base.ActionBarActivity;
import com.example.zhuangguang.common.bean.Event;
import com.example.zhuangguang.common.bean.User;
import com.example.zhuangguang.common.constants.EventAction;
import com.example.zhuangguang.common.utils.EventBusUtils;
import com.example.zhuangguang.common.utils.ToastUtils;
import com.example.zhuangguang.common.utils.UserInfoUtils;
import com.example.zhuangguang.common.widget.ObserverButton;
import com.example.zhuangguang.module_user.R;
import com.example.zhuangguang.module_user.R2;
import com.example.zhuangguang.module_user.ui.account.mvp.RegisterPresenter;
import com.example.zhuangguang.module_user.ui.account.mvp.contract.RegisterContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 庄光
 * @time 2019/7/27  22:38
 * @desc ${TODD}
 */
public class RegisterActivity extends ActionBarActivity<RegisterPresenter> implements RegisterContract.View{
    @BindView(R2.id.edt_name)
    EditText edtName;
    @BindView(R2.id.edt_pwd)
    EditText edtPwd;
    @BindView(R2.id.edt_confirm)
    EditText edtConfirm;
    @BindView(R2.id.ob_register)
    ObserverButton obRegister;
    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_activity_register;
    }

    @Override
    protected void initView() {
        setTitleText("注册");
        obRegister.observer(edtName, edtPwd, edtConfirm);
    }

    @OnClick(R2.id.ob_register)
    public void viewClick() {
        if (edtName.getText().toString().trim().length() < 6 ||
                edtPwd.getText().toString().trim().length() < 6 ||
                edtConfirm.getText().toString().trim().length() < 6) {
            ToastUtils.showToast(mContext, "用户名或密码长度至少6位！");
            return;
        }
        if (!TextUtils.equals(edtPwd.getText().toString().trim(),
                edtConfirm.getText().toString().trim())) {
            ToastUtils.showToast(mContext, "两次输入密码不一致！");
            edtPwd.setText("");
            edtConfirm.setText("");
            return;
        }
        presenter.register();
    }

    @Override
    public void onError(Object tag, String errorMsg) {
        super.onError(tag, errorMsg);
        ToastUtils.showToast(mContext, errorMsg);
    }

    @Override
    public User getUserInfo() {
        return new User(edtName.getText().toString().trim(), edtPwd.getText().toString().trim());
    }

    @Override
    public void registerSuccess(User user) {
        //注册成功 已经默认登录了
        UserInfoUtils.saveUser(user);
        EventBusUtils.sendEvent(new Event(EventAction.EVENT_REGISTER_SUCCESS));
        EventBusUtils.sendEvent(new Event(EventAction.EVENT_LOGIN_SUCCESS));
        finish();
    }
}
