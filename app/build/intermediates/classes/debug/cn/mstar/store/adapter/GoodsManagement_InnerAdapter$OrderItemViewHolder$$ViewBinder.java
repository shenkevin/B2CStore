// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.adapter;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GoodsManagement_InnerAdapter$OrderItemViewHolder$$ViewBinder<T extends cn.mstar.store.adapter.GoodsManagement_InnerAdapter.OrderItemViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558844, "field 'total_count_pres'");
    target.total_count_pres = finder.castView(view, 2131558844, "field 'total_count_pres'");
    view = finder.findRequiredView(source, 2131558849, "field 'tv_confirm_receiving'");
    target.tv_confirm_receiving = finder.castView(view, 2131558849, "field 'tv_confirm_receiving'");
    view = finder.findRequiredView(source, 2131558850, "field 'tv_evaluate'");
    target.tv_evaluate = finder.castView(view, 2131558850, "field 'tv_evaluate'");
    view = finder.findRequiredView(source, 2131558836, "field 'iv_delete'");
    target.iv_delete = finder.castView(view, 2131558836, "field 'iv_delete'");
    view = finder.findRequiredView(source, 2131558848, "field 'tv_check_shipment'");
    target.tv_check_shipment = finder.castView(view, 2131558848, "field 'tv_check_shipment'");
    view = finder.findRequiredView(source, 2131558847, "field 'tv_bottom_action_pay'");
    target.tv_bottom_action_pay = finder.castView(view, 2131558847, "field 'tv_bottom_action_pay'");
    view = finder.findRequiredView(source, 2131558835, "field 'tv_situation'");
    target.tv_situation = finder.castView(view, 2131558835, "field 'tv_situation'");
    view = finder.findRequiredView(source, 2131558845, "field 'tv_total_amount'");
    target.tv_total_amount = finder.castView(view, 2131558845, "field 'tv_total_amount'");
    view = finder.findRequiredView(source, 2131558852, "field 'linear_inner_lny_containervh'");
    target.linear_inner_lny_containervh = finder.castView(view, 2131558852, "field 'linear_inner_lny_containervh'");
  }

  @Override public void unbind(T target) {
    target.total_count_pres = null;
    target.tv_confirm_receiving = null;
    target.tv_evaluate = null;
    target.iv_delete = null;
    target.tv_check_shipment = null;
    target.tv_bottom_action_pay = null;
    target.tv_situation = null;
    target.tv_total_amount = null;
    target.linear_inner_lny_containervh = null;
  }
}
