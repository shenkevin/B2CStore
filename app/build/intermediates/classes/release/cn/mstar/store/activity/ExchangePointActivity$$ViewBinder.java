// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ExchangePointActivity$$ViewBinder<T extends cn.mstar.store.activity.ExchangePointActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558827, "field 'lny_loading_layout'");
    target.lny_loading_layout = finder.castView(view, 2131558827, "field 'lny_loading_layout'");
    view = finder.findRequiredView(source, 2131558853, "field 'lny_network_error_layout'");
    target.lny_network_error_layout = finder.castView(view, 2131558853, "field 'lny_network_error_layout'");
    view = finder.findRequiredView(source, 2131558855, "field 'lny_noresult'");
    target.lny_noresult = finder.castView(view, 2131558855, "field 'lny_noresult'");
    view = finder.findRequiredView(source, 2131558592, "field 'framelayout_main'");
    target.framelayout_main = finder.castView(view, 2131558592, "field 'framelayout_main'");
    view = finder.findRequiredView(source, 2131558651, "field 'iv_back' and method 'back'");
    target.iv_back = finder.castView(view, 2131558651, "field 'iv_back'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.back();
        }
      });
    view = finder.findRequiredView(source, 2131558903, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131558903, "field 'tv_title'");
  }

  @Override public void unbind(T target) {
    target.lny_loading_layout = null;
    target.lny_network_error_layout = null;
    target.lny_noresult = null;
    target.framelayout_main = null;
    target.iv_back = null;
    target.tv_title = null;
  }
}
