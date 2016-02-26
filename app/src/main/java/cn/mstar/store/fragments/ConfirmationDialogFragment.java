package cn.mstar.store.fragments;

import android.app.Dialog;
import android.content.Context;

public class ConfirmationDialogFragment extends Dialog {


	public ConfirmationDialogFragment(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/*@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		// Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();

		// Inflate and set the layout for the dialog
		// Pass null as the parent view because its going in the dialog layout

		builder.setView(inflater.inflate(R.layout.confirmation_dialog_box, null));
		AlertDialog dialog = builder.create (); 
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		return dialog;
	}*/

}
