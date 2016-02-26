package cn.mstar.store.utils;

import java.util.HashMap;
import java.util.Map;

import cn.mstar.store.fragments.FavoriteItemListFragment;

public class MaFragmentManagerSpace {


	private static MaFragmentManagerSpace space;
	private Map<String, FavoriteItemListFragment> frgData;

	public static MaFragmentManagerSpace getInstance() {

		if (space == null) {
			space = new MaFragmentManagerSpace ();
		}
		return space;
	}

	// get fragment

	public FavoriteItemListFragment getFragment (int position, String data) {

		FavoriteItemListFragment frg = null;

		if (frgData == null) {
			frgData = new HashMap<>();
		}

		if (frgData.get(position+1+"") == null) {
			// add it.
			FavoriteItemListFragment tmp = FavoriteItemListFragment.getInstance(data);
			frgData.put(position+1+"", tmp);
		}
		frg = frgData.get(position+1+"");
		return frg;
	}

	public boolean hasItem(int position) {
		// TODO Auto-generated method stu
		if (frgData == null)
			return false;
		else {
			if (frgData.containsKey(position+1+"")) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void flush() {
		// TODO Auto-generated method stub
		if (frgData != null)
			frgData.clear();
	}


}
