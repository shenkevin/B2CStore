package cn.mstar.store.fragments;

/**
 * Created by UlrichAbiguime at Shenzhen.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.mstar.store.R;
import cn.mstar.store.activity.ProductListActivity;
import cn.mstar.store.adapter.CatalogueAdapter;
import cn.mstar.store.adapter.ClassifySubAdapter;
import cn.mstar.store.app.Constants2;
import cn.mstar.store.app.MyAction;
import cn.mstar.store.customviews.LoadingDialog;
import cn.mstar.store.entity.MoreClassifyData;
import cn.mstar.store.entity.MoreClassifySubData;
import cn.mstar.store.interfaces.HttpRequestCallBack;
import cn.mstar.store.parse.ClassifyJsonParse;
import cn.mstar.store.utils.CustomToast;
import cn.mstar.store.utils.ImageLoadOptions;
import cn.mstar.store.utils.L;
import cn.mstar.store.utils.VolleyRequest;

/**产品分类
 * @author wenjundu 2015-7-9
 *
 */
public class ClassifyFragment extends Fragment  {

    //返回按钮
    private ImageView titleBack;
    //标题
    private TextView titleName;
    //左侧产品目录
    private ListView menuListView;
    //右侧产品
    private GridView productGridView;
    //展示图
    private ImageView showIV;
    //左侧listview适配器
    private CatalogueAdapter catalogueAdapter;
    //右侧GridView适配器
    private ClassifySubAdapter subAdapter;
    //左侧大类List
    private List<MoreClassifyData> moreClassifyList;
    //右侧小类List
    private List<MoreClassifySubData> moreClassifysubList;
    //从首页传递过来的categoryId
    private String categoryId="1064";

    @Bind(R.id.wifi_retry)  TextView tv_wifi_retry;
    @Bind(R.id.content) LinearLayout lny_content;
	/*L.d("cat::: 1064", ""+categoryId);*/

    /*	@Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_classify);
            initView();
            Utils.setNavigationBarColor(this, getResources().getColor(R.color.status_bar_color));
            Utils.setStatusBarColor(this, getResources().getColor(R.color.status_bar_color));
            initData();
        }*/
    View rootview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        rootview = LayoutInflater.from(getActivity()).inflate(
                R.layout.activity_classify, null);
        ButterKnife.bind(this,rootview);
        initView();
        hide_all(rootview);
        initData();
        return rootview;
    }


    //获取产品信息
    @OnClick(R.id.wifi_retry)
     public void initData() {
        i_showProgressDialog();
        // TODO Auto-generated method stub
        VolleyRequest.GetRequest(rootview.getContext(), Constants2.MORECLASSIFY_URL, new HttpRequestCallBack() {

            @Override
            public void onSuccess(JSONObject jsonObj) {
                // TODO Auto-generated method stub
                if (jsonObj != null) {
                    setValueToListView(jsonObj);
                    L.d("val:::", jsonObj.toString());
                }
                L.d("val:::", "null");
                i_dismissProgressDialog();
            }

            @Override
            public void onFailure(String failresult) {
                // TODO Auto-generated method stub
                i_dismissProgressDialog();
                networkExceptionError();
                CustomToast.makeToast(getActivity(), "出现异常", Toast.LENGTH_SHORT);
            }
        });
    }

    LoadingDialog dialog;
    public void i_showProgressDialog() {
        dialog = new LoadingDialog(getActivity());
        dialog.show();
    }

    public void i_dismissProgressDialog () {
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
            dialog = null;
        }
    }

    //解析json
    private void setValueToListView(JSONObject jsonObj) {
        // TODO Auto-generated method stub
        ClassifyJsonParse.parseJson(jsonObj, new ClassifyJsonParse.ParseCallBack() {

            @Override
            public void onSuccess(ArrayList<MoreClassifyData> list) {
                // TODO Auto-generated method stub
                setMoreClassifyAdapter(list);
            }


            @Override
            public void onFailure(String failresult) {
                // TODO Auto-generated method stub
                L.e(failresult);
            }
        });
    }
    //填充大类adapter
    private void setMoreClassifyAdapter(ArrayList<MoreClassifyData> list) {
        // TODO Auto-generated method stub
        if (moreClassifyList == null) {
            moreClassifyList = new ArrayList<>();
        }
        moreClassifyList.addAll(list);
        if (catalogueAdapter == null)
            catalogueAdapter = new CatalogueAdapter(getActivity(), moreClassifyList);
        catalogueAdapter.notifyDataSetChanged();
        if(moreClassifyList.size()>0){

            catalogueAdapter.setSelectedPosition(0);
            //设置横幅图片
            ImageLoader.getInstance().displayImage(moreClassifyList.get(0).getPic(), showIV, ImageLoadOptions.getOptions());
            setMoreClassifySubAdapter(moreClassifyList.get(0).getMoreclassifySubArr());
        }
//        for(int i=0;i<moreClassifyList.size();i++){
//            //选中从homefragment传递过来的categoryId类
//            if(categoryId.equals(moreClassifyList.get(i).getCategoryId())){
//                catalogueAdapter.setSelectedPosition(i);
//                //设置横幅图片
//                ImageLoader.getInstance().displayImage(moreClassifyList.get(i).getPic(), showIV, ImageLoadOptions.getOptions());
//                setMoreClassifySubAdapter(moreClassifyList.get(i).getMoreclassifySubArr());
//                break;
//            }
//        }

//		//设置横幅图片
//		ImageLoader.getInstance().displayImage(moreClassifyList.get(0).getPic(), showIV,ImageLoadOptions.getOptions());
//		setMoreClassifySubAdapter(moreClassifyList.get(0).getMoreclassifySubArr());
        hide_all(rootview);
        showResult();
    }

    private void showResult() {
        lny_content.setVisibility(View.VISIBLE);
    }

    //填充小类adapter
    private void setMoreClassifySubAdapter(
            ArrayList<MoreClassifySubData> moreclassifySubArr) {
        // TODO Auto-generated method stub
        if (moreClassifysubList != null) {
            moreClassifysubList.clear();
            moreClassifysubList.addAll(moreclassifySubArr);
            subAdapter.notifyDataSetChanged();
        }
        else {
            moreClassifysubList = new ArrayList<>();
            moreClassifysubList.addAll(moreclassifySubArr);
        }
    }

    private void initView() {

        menuListView=(ListView) findViewById(R.id.classify_list);
        productGridView=(GridView) findViewById(R.id.gv_product);
        showIV=(ImageView) findViewById(R.id.iv_product);
        titleBack=(ImageView) findViewById(R.id.title_back);
        titleName=(TextView) findViewById(R.id.title_name);
        titleName.setText(getResources().getString(R.string.more_classify));
        //设置标签
        menuListView.setTag(1);
        productGridView.setTag(2);

        menuListView.setOnItemClickListener(itemOnClick);
        productGridView.setOnItemClickListener(itemOnClick);
        moreClassifyList=new ArrayList<MoreClassifyData>();
        moreClassifysubList=new ArrayList<MoreClassifySubData>();
        catalogueAdapter=new CatalogueAdapter(rootview.getContext(), moreClassifyList);
        subAdapter=new ClassifySubAdapter(rootview.getContext(), moreClassifysubList);
        menuListView.setAdapter(catalogueAdapter);
        productGridView.setAdapter(subAdapter);
    }

    private View findViewById(int viewId) {
        if (rootview != null)
            return rootview.findViewById(viewId);
        return null;
    }

    private AdapterView.OnItemClickListener itemOnClick=new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            switch ((Integer)parent.getTag()) {
                case 1: //点击左侧目录的时候
                    //设置横幅图片
                    ImageLoader.getInstance().displayImage(moreClassifyList.get(position).getPic(), showIV,ImageLoadOptions.getOptions());
                    catalogueAdapter.setSelectedPosition(position);
                    catalogueAdapter.notifyDataSetChanged();
                    setMoreClassifySubAdapter(moreClassifyList.get(position).getMoreclassifySubArr());
                    break;

                case 2://点击右侧gridView item跳转到产品列表页
                    Intent intent =new Intent(rootview.getContext(),ProductListActivity.class);
                    intent.putExtra("categoryId", moreClassifysubList.get(position).getCategoryId());
                    intent.setAction(MyAction.classifyActivityAction);
                    startActivity(intent);
                    break;
            }
        }
    };


    protected void noResultFound() {

        hide_all(rootview);
        getLny_noresult(rootview).setVisibility(View.VISIBLE);
//
    }


    protected LinearLayout  lny_network_error_layout, lny_noresult;


    protected void initViews (View view) {
        lny_network_error_layout = (LinearLayout) view.findViewById(R.id.lny_network_error_view);
        lny_noresult = (LinearLayout) view.findViewById(R.id.lny_no_result);
    }



    protected LinearLayout getLny_network_error_layout(View v) {
        if (lny_network_error_layout == null)
            lny_network_error_layout = (LinearLayout) v.findViewById(R.id.lny_network_error_view);
        return lny_network_error_layout;
    }


    protected void hide_all (View view) {
        lny_content.setVisibility(View.GONE);
        getLny_network_error_layout(view).setVisibility(View.GONE);
        getLny_noresult(view).setVisibility(View.GONE);
    }


    protected void networkExceptionError() {

        hide_all(rootview);
        getLny_network_error_layout(rootview).setVisibility(View.VISIBLE);
    }



    protected LinearLayout getLny_noresult(View v) {
        if (lny_noresult == null)
            lny_noresult = (LinearLayout) v.findViewById(R.id.lny_no_result);
        return lny_noresult;
    }

    
}
