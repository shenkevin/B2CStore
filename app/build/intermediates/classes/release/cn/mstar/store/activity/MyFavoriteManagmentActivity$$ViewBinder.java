// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyFavoriteManagmentActivity$$ViewBinder<T extends cn.mstar.store.activity.MyFavoriteManagmentActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558827, "field 'lny_loading_layout'");
    target.lny_loading_layout = finder.castView(view, 2131558827, "field 'lny_loading_layout'");
    view = finder.findRequiredView(source, 2131558853, "field 'lny_network_error_layout'");
    target.lny_network_error_layout = finder.castView(view, 2131558853, "field 'lny_network_error_layout'");
    view = finder.findRequiredView(source, 2131558855, "field 'lny_noresult'");
    target.lny_noresult = finder.castView(view, 2131558855, "field 'lny_noresult'");
    view = finder.findRequiredView(source, 2131558594, "field 'lny_content'");
    target.lny_content = finder.castView(view, 2131558594, "field 'lny_content'");
    view = finder.findRequiredView(source, 2131558854, "method 'reloadData'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.reloadData();
        }
      });
  }

  @Override public void unbind(T target) {
    target.lny_loading_layout = null;
    target.lny_network_error_layout = null;
    target.lny_noresult = null;
    target.lny_content = null;
  }
}
