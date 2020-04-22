// Generated code from Butter Knife. Do not modify!
package cn.tedu.bmobdemo.ui;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends cn.tedu.bmobdemo.ui.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230725, "field 'etUsername'");
    target.etUsername = finder.castView(view, 2131230725, "field 'etUsername'");
    view = finder.findRequiredView(source, 2131230726, "field 'etPassword'");
    target.etPassword = finder.castView(view, 2131230726, "field 'etPassword'");
    view = finder.findRequiredView(source, 2131230727, "field 'rgGender'");
    target.rgGender = finder.castView(view, 2131230727, "field 'rgGender'");
    view = finder.findRequiredView(source, 2131230724, "field 'ivAvatar'");
    target.ivAvatar = finder.castView(view, 2131230724, "field 'ivAvatar'");
    view = finder.findRequiredView(source, 2131230730, "method 'submit'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.submit(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.etUsername = null;
    target.etPassword = null;
    target.rgGender = null;
    target.ivAvatar = null;
  }
}
