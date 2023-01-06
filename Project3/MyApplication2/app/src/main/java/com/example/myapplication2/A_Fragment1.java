package com.example.myapplication2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

// Several Activity and Fragment lifecycle methods are instrumented to emit LogCat output
//  so you can follow the class' lifecycle
// MH 7//7/2020 original Fragment class is deprecated (as was ListFragment).
// Added the androidx.appcompat support library in the gradle module build file
public class A_Fragment1 extends ListFragment {
	private static final String TAG = "TitlesFragment";
	// private ListSelectionListener mListener = null;
	private Z_ListViewModel model;

	// Callback interface that allows this Fragment to notify the QuoteViewerActivity when  
	// user clicks on a List Item  
	//	public interface ListSelectionListener {
	//		void onListSelection(int index);
	//	}

	// Called when the user selects an item from the List
	@Override
	public void onListItemClick(@NonNull ListView l, @NonNull View v, int pos, long id) {

		// Indicates the selected item has been checked
		getListView().setItemChecked(pos, true);

		// Inform the ModelView that the selection may have changed
		model.selectItem(pos);

		// Inform the QuoteViewerActivity that the item in position pos has been selected
		// mListener.onListSelection(pos);
	}

	@Override
	public void onAttach(@NonNull Context activity) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
		super.onAttach(activity);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
		super.onCreate(savedInstanceState);
	}

	// UB:  Notice that the superclass's method does an OK job of inflating the
	//      container layout.
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
		return super.onCreateView(inflater, container, savedInstanceState);
	}


	// UB: 2/19/2022 - Changed from onActivityCreated() callback
	@Override
	public void onViewCreated(View view, Bundle savedState) {
		Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
		super.onViewCreated(view, savedState);

		model = new ViewModelProvider(requireActivity()).get(Z_ListViewModel.class);

		// Set the list adapter for the ListView 
		// Discussed in more detail in the user interface classes lesson  
		setListAdapter(new ArrayAdapter<>(getActivity(),
				R.layout.titles, A_Activity.mTitleArray));

		// Set the list choice mode to allow only one selection at a time
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	@Override
	public void onStart() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
		super.onStop();
	}

	@Override
	public void onDetach() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
		super.onDetach();
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
		super.onDestroyView();
	}

}