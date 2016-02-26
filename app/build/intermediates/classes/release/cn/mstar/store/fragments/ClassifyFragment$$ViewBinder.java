// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ClassifyFragment$$ViewBinder<T extends cn.mstar.store.fragments.ClassifyFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558854, "field 'tv_wifi_retry' and method 'initData'");
    target.tv_wifi_retry = finder.castView(view, 2131558854, "field 'tv_wifi_retry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.initData();
        }
      });
    view = finder.findRequiredView(source, 2131558518, "field 'lny_content'");
    target.lny_content = finder.castView(view, 2131558518, "field 'lny_content'");
  }

  @Override public void unbind(T target) {
    target.tv_wifi_retry = null;
    target.lny_content = null;
  }
}
