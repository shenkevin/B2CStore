// Generated code from Butter Knife. Do not modify!
package cn.mstar.store.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class IndentDetailsActivity$$ViewBinder<T extends cn.mstar.store.activity.IndentDetailsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558567, "field 'btIndentOperation'");
    target.btIndentOperation = finder.castView(view, 2131558567, "field 'btIndentOperation'");
    view = finder.findRequiredView(source, 2131558793, "field 'tv_goods_number_tv'");
    target.tv_goods_number_tv = finder.castView(view, 2131558793, "field 'tv_goods_number_tv'");
    view = finder.findRequiredView(source, 2131558794, "field 'tv_indent_status_tv'");
    target.tv_indent_status_tv = finder.castView(view, 2131558794, "field 'tv_indent_status_tv'");
    view = finder.findRequiredView(source, 2131558791, "field 'tv_look_logistics_tv'");
    target.tv_look_logistics_tv = finder.castView(view, 2131558791, "field 'tv_look_logistics_tv'");
    view = finder.findRequiredView(source, 2131558525, "field 'tv_receiver'");
    target.tv_receiver = finder.castView(view, 2131558525, "field 'tv_receiver'");
    view = finder.findRequiredView(source, 2131558526, "field 'tv_receiver_phone'");
    target.tv_receiver_phone = finder.castView(view, 2131558526, "field 'tv_receiver_phone'");
    view = finder.findRequiredView(source, 2131558527, "field 'tv_receives_an_address'");
    target.tv_receives_an_address = finder.castView(view, 2131558527, "field 'tv_receives_an_address'");
    view = finder.findRequiredView(source, 2131558786, "field 'tv_goods_total_prices_tv'");
    target.tv_goods_total_prices_tv = finder.castView(view, 2131558786, "field 'tv_goods_total_prices_tv'");
    view = finder.findRequiredView(source, 2131558789, "field 'tv_actual_payment_tv'");
    target.tv_actual_payment_tv = finder.castView(view, 2131558789, "field 'tv_actual_payment_tv'");
    view = finder.findRequiredView(source, 2131558790, "field 'tv_order_time_tv'");
    target.tv_order_time_tv = finder.castView(view, 2131558790, "field 'tv_order_time_tv'");
    view = finder.findRequiredView(source, 2131558568, "field 'btn_request_sendBack'");
    target.btn_request_sendBack = finder.castView(view, 2131558568, "field 'btn_request_sendBack'");
    view = finder.findRequiredView(source, 2131558570, "field 'btn_pay_now'");
    target.btn_pay_now = finder.castView(view, 2131558570, "field 'btn_pay_now'");
  }

  @Override public void unbind(T target) {
    target.btIndentOperation = null;
    target.tv_goods_number_tv = null;
    target.tv_indent_status_tv = null;
    target.tv_look_logistics_tv = null;
    target.tv_receiver = null;
    target.tv_receiver_phone = null;
    target.tv_receives_an_address = null;
    target.tv_goods_total_prices_tv = null;
    target.tv_actual_payment_tv = null;
    target.tv_order_time_tv = null;
    target.btn_request_sendBack = null;
    target.btn_pay_now = null;
  }
}
