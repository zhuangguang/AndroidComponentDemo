// Generated code from Butter Knife. Do not modify!
package com.example.zhuangguang.module_user.ui.main.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.zhuangguang.module_user.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserFragment_ViewBinding<T extends UserFragment> implements Unbinder {
  protected T target;

  private View view2131493106;

  private View view2131493105;

  private View view2131493000;

  private View view2131492998;

  private View view2131492999;

  private View view2131492997;

  @UiThread
  public UserFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.ivBg = Utils.findRequiredViewAsType(source, R.id.iv_bg, "field 'ivBg'", ImageView.class);
    target.ivHead = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'ivHead'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_login_out, "field 'tvLoginOut' and method 'onViewClicked'");
    target.tvLoginOut = Utils.castView(view, R.id.tv_login_out, "field 'tvLoginOut'", TextView.class);
    view2131493106 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llLogged = Utils.findRequiredViewAsType(source, R.id.ll_logged, "field 'llLogged'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_login, "field 'tvLogin' and method 'onViewClicked'");
    target.tvLogin = Utils.castView(view, R.id.tv_login, "field 'tvLogin'", TextView.class);
    view2131493105 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvEmail = Utils.findRequiredViewAsType(source, R.id.tv_email, "field 'tvEmail'", TextView.class);
    view = Utils.findRequiredView(source, R.id.miv_order, "method 'onViewClicked'");
    view2131493000 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.miv_check, "method 'onViewClicked'");
    view2131492998 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.miv_collection, "method 'onViewClicked'");
    view2131492999 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.miv_about, "method 'onViewClicked'");
    view2131492997 = view;
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

    target.ivBg = null;
    target.ivHead = null;
    target.tvLoginOut = null;
    target.llLogged = null;
    target.tvLogin = null;
    target.tvName = null;
    target.tvEmail = null;

    view2131493106.setOnClickListener(null);
    view2131493106 = null;
    view2131493105.setOnClickListener(null);
    view2131493105 = null;
    view2131493000.setOnClickListener(null);
    view2131493000 = null;
    view2131492998.setOnClickListener(null);
    view2131492998 = null;
    view2131492999.setOnClickListener(null);
    view2131492999 = null;
    view2131492997.setOnClickListener(null);
    view2131492997 = null;

    this.target = null;
  }
}
