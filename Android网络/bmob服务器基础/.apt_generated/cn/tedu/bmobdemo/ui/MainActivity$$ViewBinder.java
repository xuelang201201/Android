// Generated code from Butter Knife. Do not modify!
package cn.tedu.bmobdemo.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends cn.tedu.bmobdemo.ui.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230720, "field 'etName'");
    target.etName = finder.castView(view, 2131230720, "field 'etName'");
    view = finder.findRequiredView(source, 2131230721, "field 'etPwd'");
    target.etPwd = finder.castView(view, 2131230721, "field 'etPwd'");
    view = finder.findRequiredView(source, 2131230723, "field 'btnRegister' and method 'register'");
    target.btnRegister = finder.castView(view, 2131230723, "field 'btnRegister'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.register();
        }
      });
    view = finder.findRequiredView(source, 2131230722, "field 'btnLogin' and method 'login'");
    target.btnLogin = finder.castView(view, 2131230722, "field 'btnLogin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login();
        }
      });
  }

  @Override public void unbind(T target) {
    target.etName = null;
    target.etPwd = null;
    target.btnRegister = null;
    target.btnLogin = null;
  }
}
