// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SelfInformationActivity$$ViewBinder<T extends cn.mstar.store.activity.SelfInformationActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558674, "field 'iv_head'");
    target.iv_head = finder.castView(view, 2131558674, "field 'iv_head'");
    view = finder.findRequiredView(source, 2131558671, "field 'loading'");
    target.loading = finder.castView(view, 2131558671, "field 'loading'");
    view = finder.findRequiredView(source, 2131558480, "field 'scrollview'");
    target.scrollview = finder.castView(view, 2131558480, "field 'scrollview'");
  }

  @Override public void unbind(T target) {
    target.iv_head = null;
    target.loading = null;
    target.scrollview = null;
  }
}
