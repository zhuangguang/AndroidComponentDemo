package com.example.zhuangguang.module_user.ui.account;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.zhuangguang.common.base.ActionBarActivity;
import com.example.zhuangguang.common.bean.Event;
import com.example.zhuangguang.common.bean.User;
import com.example.zhuangguang.common.constants.ARouterConfig;
import com.example.zhuangguang.common.constants.EventAction;
import com.example.zhuangguang.common.utils.ActivityToActivity;
import com.example.zhuangguang.common.utils.EventBusUtils;
import com.example.zhuangguang.common.utils.ToastUtils;
import com.example.zhuangguang.common.utils.UserInfoUtils;
import com.example.zhuangguang.common.widget.ObserverButton;
import com.example.zhuangguang.module_user.R;
import com.example.zhuangguang.module_user.R2;
import com.example.zhuangguang.module_user.ui.account.mvp.LoginPresenter;
import com.example.zhuangguang.module_user.ui.account.mvp.contract.LoginContract;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 庄光
 * @time 2019/7/27  18:53
 * @desc ${TODD}
 */
@Route(path = ARouterConfig.USER_LOGIN)
public class LoginActivity extends ActionBarActivity<LoginPresenter> implements LoginContract.View{

    @BindView(R2.id.edt_name)
    EditText edtName;

    @BindView(R2.id.edt_pwd)
    EditText edtPwd;

    @BindView(R2.id.ob_login)
    ObserverButton obLogin;

    @BindView(R2.id.ob_register)
    TextView obRegister;
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_activity_login;
    }

    @Override
    protected void initView() {
        setTitleText("登录");
        obLogin.observer(edtName, edtPwd);
    }

    @OnClick({R2.id.ob_login, R2.id.ob_register})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.ob_login) {
            presenter.login();
        } else if (i == R.id.ob_register) {
            ActivityToActivity.toActivity(mContext, RegisterActivity.class);
        }
    }

    @Override
    public User getUserInfo() {
        return new User(edtName.getText().toString().trim(), edtPwd.getText().toString().trim());
    }

    @Override
    public void loginSuccess(User user) {
        UserInfoUtils.saveUser(user);
        EventBusUtils.sendEvent(new Event(EventAction.EVENT_LOGIN_SUCCESS));
        finish();
    }

    @Override
    public void onError(Object tag, String errorMsg) {
        super.onError(tag, errorMsg);
        ToastUtils.showToast(mContext, errorMsg);
    }

    @Override
    public void onEventBus(Event event) {
        super.onEventBus(event);
        if (TextUtils.equals(event.getAction(), EventAction.EVENT_REGISTER_SUCCESS)) {
            finish();
        }
    }

    @Override
    protected boolean regEvent() {
        return true;
    }
}
