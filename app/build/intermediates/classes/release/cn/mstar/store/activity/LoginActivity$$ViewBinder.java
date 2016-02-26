// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends cn.mstar.store.activity.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558573, "field 'ed_username'");
    target.ed_username = finder.castView(view, 2131558573, "field 'ed_username'");
    view = finder.findRequiredView(source, 2131558577, "field 'ed_password'");
    target.ed_password = finder.castView(view, 2131558577, "field 'ed_password'");
    view = finder.findRequiredView(source, 2131558574, "field 'iv_username_cross'");
    target.iv_username_cross = finder.castView(view, 2131558574, "field 'iv_username_cross'");
    view = finder.findRequiredView(source, 2131558903, "field 'tv_actionbar_middle'");
    target.tv_actionbar_middle = finder.castView(view, 2131558903, "field 'tv_actionbar_middle'");
    view = finder.findRequiredView(source, 2131558905, "field 'tv_actionbar_right'");
    target.tv_actionbar_right = finder.castView(view, 2131558905, "field 'tv_actionbar_right'");
    view = finder.findRequiredView(source, 2131558651, "field 'iv_actionbar_left'");
    target.iv_actionbar_left = finder.castView(view, 2131558651, "field 'iv_actionbar_left'");
    view = finder.findRequiredView(source, 2131558579, "field 'forgetPwdTV'");
    target.forgetPwdTV = finder.castView(view, 2131558579, "field 'forgetPwdTV'");
    view = finder.findRequiredView(source, 2131558580, "method 'register'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.register(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.ed_username = null;
    target.ed_password = null;
    target.iv_username_cross = null;
    target.tv_actionbar_middle = null;
    target.tv_actionbar_right = null;
    target.iv_actionbar_left = null;
    target.forgetPwdTV = null;
  }
}
