package cn.mstar.store.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.net.URLEncoder;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mstar.store.R;
import cn.mstar.store.app.MyApplication;
import cn.mstar.store.entity.BuyProductEntity;
import cn.mstar.store.utils.ImageLoadOptions;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.NewLink;
import cn.mstar.store.utils.Utils;
import cn.mstar.store.utils.VolleyRequest;

public class PostCommentActivity extends AppCompatActivity {


    @Bind(R.id.radio_group)
    RadioGroup radioGroup;
    @Bind({R.id.ck_good, R.id.ck_fair, R.id.ck_bad})
    List<RadioButton> rb;
    String[] group;
    @Bind(R.id.product_img) ImageView iv_product_img;
    @Bind(R.id.product_name) TextView tv_product_name;
    @Bind(R.id.product_norms) TextView tv_product_norms;
    @Bind(R.id.btn_camera) ImageView iv_btn_camera;
    @Bind(R.id.editext) EditText edittext;
    // vars
    BuyProductEntity entity;
    private MyApplication app;
    private int scores;
    private String content;
    private String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);
        ButterKnife.bind(this);
        app = (MyApplication) getApplication();
        Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
        Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
        entity = (BuyProductEntity) getIntent().getSerializableExtra("data");
        orderId = getIntent().getStringExtra("orderid");
        inflateTopViews();
        group = getResources().getStringArray(R.array.comment_group);

        final int[] bgId_down = new int[]{R.drawable.icon_praise_down, R.drawable.icon_general_down, R.drawable.icon_bad_down};
        final int[] bgId_up = new int[]{R.drawable.icon_praise_nor, R.drawable.icon_general_nor, R.drawable.icon_bad_nor};

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup grp, int checkedId) {
                int i = 0;
                switch (checkedId) {
                    case R.id.ck_bad:
                        i = 2;
                        break;
                    case R.id.ck_fair:
                        i = 1;
                        break;
                    case R.id.ck_good:
                        i = 0;
                        break;
                }
                for (int e = 0;  e< 3; e++) {
                    if (i == e) {
                        rb.get(e).setText(group[e]);
                        rb.get(e).setBackgroundResource(bgId_down[e]);
                    } else {
                        rb.get(e).setText("");
                        rb.get(e).setBackgroundResource(bgId_up[e]);
                    }
                }
                scores = i+1;
            }
        });
        radioGroup.check(R.id.ck_good);

        setupTitle (getString(R.string.product_comment));

    }

    private void setupTitle(String string) {
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText(string);
    }

    private void inflateTopViews() {
        /*@Bind(R.id.product_img) ImageView iv_product_img;
@Bind(R.id.product_name) TextView tv_product_name;
@Bind(R.id.product_norms) TextView tv_product_norms;*/

        L.d("XXX", entity.toString());

        tv_product_name.setText(entity.getProduct().getName());
        tv_product_norms.setText(entity.getNorms());
        ImageLoader.getInstance().displayImage(entity.getProduct().getImageUrl(), iv_product_img, ImageLoadOptions.getOptions());
    }

    //  @OnClick (R.id.btn_camera)
    public void selectPicFromGallery () {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.select_picture)), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null) {
            String[] all_path = data.getStringArrayExtra("all_path");

           /* ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();
            for (String string : all_path) {
                CustomGallery item = new CustomGallery();
                item.sdcardPath = string;
                dataT.add(item);
            }*/
           /* viewSwitcher.setDisplayedChild(0);
            adapter.addAll(dataT);*/
        }
        sendComment();
    }



    @OnClick (R.id.bt_send_comment)
    public void sendComment() {
        content = edittext.getText().toString();
        if (content != null && content.length() < 3) {
            maketoast(getString(R.string.comment_not_less_than_3));
            return;
        }
        switch (scores) {
            case 1:
                scores = 3;
                break;
            case 3:
                scores = 1;
                break;
        }
        String link = NewLink.SUBMIT_COMMENT+"&key="+ Utils.getTokenKey(app)+"&OrderNum="+orderId+
                "&goods_id="+entity.getProduct().getProId()+"&scores="+scores;
        try {
            String c = "&content="+ URLEncoder.encode(content, "UTF-8");
            link+=c;
        }catch (Exception e) {
            e.printStackTrace();
            String c = "&content="+content;
            L.d("link:::", "no!!! "+c);
            link+= c;
        }
        L.d("link:::", link);

        VolleyRequest.GetCookieRequest(this, link, new VolleyRequest.HttpStringRequsetCallBack() {
            @Override
            public void onSuccess(String result) {
                L.d("result:::", result);
                maketoast(getString(R.string.send_comment_success));
                finish();
            }

            @Override
            public void onFail(String error) {
                maketoast(getString(R.string.send_comment_error));
            }
        });
    }

    private void maketoast(String string) {

        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
