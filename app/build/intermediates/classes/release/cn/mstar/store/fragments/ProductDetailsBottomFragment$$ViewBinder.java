// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProductDetailsBottomFragment$$ViewBinder<T extends cn.mstar.store.fragments.ProductDetailsBottomFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558775, "field 'mViewpager'");
    target.mViewpager = finder.castView(view, 2131558775, "field 'mViewpager'");
    view = finder.findRequiredView(source, 2131558563, "field 'tabs'");
    target.tabs = finder.castView(view, 2131558563, "field 'tabs'");
    view = finder.findRequiredView(source, 2131558774, "field 'rel'");
    target.rel = finder.castView(view, 2131558774, "field 'rel'");
  }

  @Override public void unbind(T target) {
    target.mViewpager = null;
    target.tabs = null;
    target.rel = null;
  }
}
