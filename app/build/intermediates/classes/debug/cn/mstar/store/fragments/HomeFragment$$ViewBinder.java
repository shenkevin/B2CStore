// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeFragment$$ViewBinder<T extends cn.mstar.store.fragments.HomeFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558752, "field 'webview_progressbar'");
    target.webview_progressbar = finder.castView(view, 2131558752, "field 'webview_progressbar'");
    view = finder.findRequiredView(source, 2131558854, "method 'initData'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.initData();
        }
      });
  }

  @Override public void unbind(T target) {
    target.webview_progressbar = null;
  }
}
