// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ShoppingCartFragment$$ViewBinder<T extends cn.mstar.store.fragments.ShoppingCartFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558680, "field 'ck_checkAll'");
    target.ck_checkAll = finder.castView(view, 2131558680, "field 'ck_checkAll'");
    view = finder.findRequiredView(source, 2131558778, "field 'tv_totalPrice'");
    target.tv_totalPrice = finder.castView(view, 2131558778, "field 'tv_totalPrice'");
    view = finder.findRequiredView(source, 2131558681, "field 'bt_go_pay'");
    target.bt_go_pay = finder.castView(view, 2131558681, "field 'bt_go_pay'");
    view = finder.findRequiredView(source, 2131558854, "field 'tv_wifi_retry' and method 'inflateDatas'");
    target.tv_wifi_retry = finder.castView(view, 2131558854, "field 'tv_wifi_retry'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.inflateDatas();
        }
      });
    view = finder.findRequiredView(source, 2131558903, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131558903, "field 'tv_title'");
  }

  @Override public void unbind(T target) {
    target.ck_checkAll = null;
    target.tv_totalPrice = null;
    target.bt_go_pay = null;
    target.tv_wifi_retry = null;
    target.tv_title = null;
  }
}
