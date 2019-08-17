// Generated code from Butter Knife. Do not modify!
package com.example.zhuangguang.module_user.ui.account;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.zhuangguang.common.widget.ObserverButton;
import com.example.zhuangguang.module_user.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding<T extends LoginActivity> implements Unbinder {
  protected T target;

  private View view2131493010;

  private View view2131493011;

  @UiThread
  public LoginActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.edtName = Utils.findRequiredViewAsType(source, R.id.edt_name, "field 'edtName'", EditText.class);
    target.edtPwd = Utils.findRequiredViewAsType(source, R.id.edt_pwd, "field 'edtPwd'", EditText.class);
    view = Utils.findRequiredView(source, R.id.ob_login, "field 'obLogin' and method 'onViewClicked'");
    target.obLogin = Utils.castView(view, R.id.ob_login, "field 'obLogin'", ObserverButton.class);
    view2131493010 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ob_register, "field 'obRegister' and method 'onViewClicked'");
    target.obRegister = Utils.castView(view, R.id.ob_register, "field 'obRegister'", TextView.class);
    view2131493011 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.edtName = null;
    target.edtPwd = null;
    target.obLogin = null;
    target.obRegister = null;

    view2131493010.setOnClickListener(null);
    view2131493010 = null;
    view2131493011.setOnClickListener(null);
    view2131493011 = null;

    this.target = null;
  }
}
