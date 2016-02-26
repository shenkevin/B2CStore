// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GoodsManagementActivity$$ViewBinder<T extends cn.mstar.store.activity.GoodsManagementActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558564, "field 'mViewpager'");
    target.mViewpager = finder.castView(view, 2131558564, "field 'mViewpager'");
    view = finder.findRequiredView(source, 2131558563, "field 'tabs'");
    target.tabs = finder.castView(view, 2131558563, "field 'tabs'");
    view = finder.findRequiredView(source, 2131558903, "field 'tv_middle'");
    target.tv_middle = finder.castView(view, 2131558903, "field 'tv_middle'");
    view = finder.findRequiredView(source, 2131558651, "field 'iv_title_back' and method 'back'");
    target.iv_title_back = finder.castView(view, 2131558651, "field 'iv_title_back'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.back();
        }
      });
    view = finder.findRequiredView(source, 2131558593, "field 'titleLayout'");
    target.titleLayout = finder.castView(view, 2131558593, "field 'titleLayout'");
  }

  @Override public void unbind(T target) {
    target.mViewpager = null;
    target.tabs = null;
    target.tv_middle = null;
    target.iv_title_back = null;
    target.titleLayout = null;
  }
}
