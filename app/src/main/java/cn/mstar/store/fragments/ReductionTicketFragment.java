package cn.mstar.store.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.mstar.store.R;
import cn.mstar.store.adapter.ReductionTicketsLvAdapter;


public class ReductionTicketFragment extends Fragment {





	private static final String POSITION = "position";
private ListView lv_items;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View  view = inflater.inflate(R.layout.fragment_reduction_ticket_item, null);

		int position = getArguments().getInt(POSITION);

		if (true/*position == 1*/) {
			// show all the elements.
			
			// create an adapter and add the items inside.
			initViews (view);
			// set up adapter
			lv_items.setAdapter(new ReductionTicketsLvAdapter(getActivity(), null));
		}

		return view;
	}



	private void initViews(View view) {
		lv_items = (ListView) view.findViewById(R.id.lv_reduction_items);
	}



	public static android.support.v4.app.Fragment getInstance(int i) {
		android.support.v4.app.Fragment fr = new ReductionTicketFragment();
		Bundle args = new Bundle();
		args.putInt(POSITION, i);
		fr.setArguments(args);
		return fr;
	}


}
