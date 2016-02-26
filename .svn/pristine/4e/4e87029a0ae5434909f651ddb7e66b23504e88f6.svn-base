package cn.mstar.store.testing;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.mstar.store.R;
import cn.mstar.store.activity.MockActivity;
import cn.mstar.store.utils.ImageLoadOptions;

import com.nostra13.universalimageloader.core.ImageLoader;


public class ImageFragment extends Fragment {

	private static final String LINK = "http://yl.2500sz.com/ylpd/mx/gtxw/images/2015/2/27/2015227DDCDC52967F8476A82B502ECC20C486F.jpg";
	ImageView imageview;
	Context context = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view =  inflater.inflate(R.layout.fragment_image, container, false);
		context = view.getContext();
		imageview = (ImageView) view.findViewById(R.id.imageview);

		/*Bitmap bitmap = decodeSampledBitmapFromResource(getResources(), R.drawable.come_on, 400, 400);
		imageview.setImageBitmap(bitmap);*/
		
		// launch volley request to get the picture...
				ImageLoader.getInstance().displayImage(LINK, imageview, ImageLoadOptions.getOptions());
		return view;
	}


	public   int getScreenHeigth () {

		DisplayMetrics metrics = new DisplayMetrics();
		((MockActivity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.heightPixels;
	}

	public   int getScreenWidth () {

		DisplayMetrics metrics = new DisplayMetrics();
		((MockActivity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}


	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
			int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
				final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int calculateInSampleSize(
			BitmapFactory.Options options, int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

}
