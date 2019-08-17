// Generated code from Butter Knife. Do not modify!
package com.example.zhuangguang.module_user.ui.about;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.zhuangguang.module_user.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AboutActivity_ViewBinding<T extends AboutActivity> implements Unbinder {
  protected T target;

  @UiThread
  public AboutActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mTvVersion = Utils.findRequiredViewAsType(source, R.id.tv_version, "field 'mTvVersion'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTvVersion = null;

    this.target = null;
  }
}
