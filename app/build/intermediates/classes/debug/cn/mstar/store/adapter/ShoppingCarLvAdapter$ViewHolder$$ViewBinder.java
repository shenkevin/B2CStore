// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ShoppingCarLvAdapter$ViewHolder$$ViewBinder<T extends cn.mstar.store.adapter.ShoppingCarLvAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558893, "field 'ck_category_name'");
    target.ck_category_name = finder.castView(view, 2131558893, "field 'ck_category_name'");
    view = finder.findRequiredView(source, 2131558723, "field 'iv_item_pic'");
    target.iv_item_pic = finder.castView(view, 2131558723, "field 'iv_item_pic'");
    view = finder.findRequiredView(source, 2131558722, "field 'iv_delete_icon'");
    target.iv_delete_icon = finder.castView(view, 2131558722, "field 'iv_delete_icon'");
    view = finder.findRequiredView(source, 2131558666, "field 'iv_plus'");
    target.iv_plus = finder.castView(view, 2131558666, "field 'iv_plus'");
    view = finder.findRequiredView(source, 2131558665, "field 'iv_sous'");
    target.iv_sous = finder.castView(view, 2131558665, "field 'iv_sous'");
    view = finder.findRequiredView(source, 2131558724, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131558724, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131558725, "field 'tv_unit_price'");
    target.tv_unit_price = finder.castView(view, 2131558725, "field 'tv_unit_price'");
    view = finder.findRequiredView(source, 2131558895, "field 'tv_item_count_static'");
    target.tv_item_count_static = finder.castView(view, 2131558895, "field 'tv_item_count_static'");
    view = finder.findRequiredView(source, 2131558536, "field 'tv_item_count_dynamic'");
    target.tv_item_count_dynamic = finder.castView(view, 2131558536, "field 'tv_item_count_dynamic'");
    view = finder.findRequiredView(source, 2131558894, "field 'tv_product_commodities'");
    target.tv_product_commodities = finder.castView(view, 2131558894, "field 'tv_product_commodities'");
  }

  @Override public void unbind(T target) {
    target.ck_category_name = null;
    target.iv_item_pic = null;
    target.iv_delete_icon = null;
    target.iv_plus = null;
    target.iv_sous = null;
    target.tv_title = null;
    target.tv_unit_price = null;
    target.tv_item_count_static = null;
    target.tv_item_count_dynamic = null;
    target.tv_product_commodities = null;
  }
}
