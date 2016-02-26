package cn.mstar.store.fragments;

import java.util.ArrayList;
import java.util.List;

import cn.mstar.store.R;
import cn.mstar.store.activity.ProductListActivity;
import cn.mstar.store.adapter.HotSearchAdapter;
import cn.mstar.store.app.MyAction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HotFragment extends Fragment {

	
	private ListView listView;
	private HotSearchAdapter adapter;
	//存放搜索数据的List
	private List<String> list;
	private Context context;
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		context=getActivity();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		listView=(ListView) getView().findViewById(R.id.hot_search_list);
		list=new ArrayList<String>();
		list.add("黄金");
		list.add("珠宝");
		adapter=new HotSearchAdapter(getActivity(), list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(context,ProductListActivity.class);
				intent.setAction(MyAction.searchActivitryAction);
				intent.putExtra("key", list.get(position));
				startActivity(intent);
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fragment_hot_search, null);
		return view;
	}

}
