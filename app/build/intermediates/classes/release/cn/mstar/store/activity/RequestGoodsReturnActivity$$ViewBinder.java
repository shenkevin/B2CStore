// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RequestGoodsReturnActivity$$ViewBinder<T extends cn.mstar.store.activity.RequestGoodsReturnActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558903, "field 'titlebar_title'");
    target.titlebar_title = finder.castView(view, 2131558903, "field 'titlebar_title'");
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
    view = finder.findRequiredView(source, 2131558645, "method 'sendingRequest'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.sendingRequest();
        }
      });
  }

  @Override public void unbind(T target) {
    target.titlebar_title = null;
    target.iv_back = null;
  }
}
