// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PayingTransactionSuccessActivity$$ViewBinder<T extends cn.mstar.store.activity.PayingTransactionSuccessActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
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
    view = finder.findRequiredView(source, 2131558607, "field 'tv_price'");
    target.tv_price = finder.castView(view, 2131558607, "field 'tv_price'");
    view = finder.findRequiredView(source, 2131558608, "method 'goToProductDetails'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.goToProductDetails();
        }
      });
    view = finder.findRequiredView(source, 2131558609, "method 'goToHome'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.goToHome();
        }
      });
  }

  @Override public void unbind(T target) {
    target.iv_back = null;
    target.tv_title = null;
    target.tv_price = null;
  }
}
