// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ReductionActivity$$ViewBinder<T extends cn.mstar.store.activity.ReductionActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558635, "field 'mViewpager'");
    target.mViewpager = finder.castView(view, 2131558635, "field 'mViewpager'");
    view = finder.findRequiredView(source, 2131558634, "field 'tabs'");
    target.tabs = finder.castView(view, 2131558634, "field 'tabs'");
    view = finder.findRequiredView(source, 2131558651, "field 'iv_back'");
    target.iv_back = finder.castView(view, 2131558651, "field 'iv_back'");
    view = finder.findRequiredView(source, 2131558903, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131558903, "field 'tv_title'");
  }

  @Override public void unbind(T target) {
    target.mViewpager = null;
    target.tabs = null;
    target.iv_back = null;
    target.tv_title = null;
  }
}
