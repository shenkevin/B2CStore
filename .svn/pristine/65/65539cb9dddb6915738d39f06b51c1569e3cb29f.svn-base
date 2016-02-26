package cn.mstar.store.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mstar.store.R;
import cn.mstar.store.app.MyAction;
import cn.mstar.store.customviews.LoadingDialog;
import cn.mstar.store.utils.Utils;

public class PayingTransactionSuccessActivity extends BaseActivity {

    private String orderId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying_transaction_success);
        Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
        Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
        ButterKnife.bind(this);
        setMyTitle(getString(R.string.transaction_success));
        iv_back.setVisibility(View.VISIBLE);
        Double price  = getIntent().getDoubleExtra("price", 0);
        orderId = getIntent().getStringExtra("orderid");
    /*    L.d("data:::", "orderId --> "+orderId);
        L.d("data:::", "price --> "+price);*/
        tv_price.setText(getString(R.string.renminbi)+Utils.formatedPrice(price));

        // 订单支付成功的接口

    }


    //actionbar
    @OnClick(R.id.title_back)
    public void back () {
        finish ();
    }
    @Bind(R.id.title_back)
    ImageView iv_back;
    @Bind(R.id.title_name)
    TextView tv_title;
    @Bind(R.id.tv_price)
    TextView tv_price;

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void setMyTitle(String title) {
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(title);

    }

    LoadingDialog dialog;
    public void i_showProgressDialog() {
        dialog = new LoadingDialog(this);
        dialog.show();
    }

    public void i_dismissProgressDialog () {
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
            dialog = null;
        }
    }


    @OnClick(R.id.tv_product_details)
    public void goToProductDetails () {

        // 返回到首页，并打开新的详情页
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setAction(MyAction.paySuccessGotodetails);
        // orderId has to be added.
        intent.putExtra("data", orderId);
        startActivity(intent);
        finish();

    }

    @OnClick(R.id.tv_return_home)
    public void goToHome () {

//    返回到首页
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_paying_transaction_success, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
