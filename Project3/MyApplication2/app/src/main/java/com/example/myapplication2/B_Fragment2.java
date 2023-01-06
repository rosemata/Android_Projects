package com.example.myapplication2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

//Several Activity and Fragment lifecycle methods are instrumented to emit LogCat output
//so you can follow the class' lifecycle
// MH 7/7/2020 -- Changed deprecated Fragment class to support.v4 version.
// Added as dependency in gradle module build file
public class B_Fragment2 extends Fragment {

	private static final String TAG = "QuotesFragment";

	private TextView mQuoteView = null;
	private int mCurrIdx = 0;
	private int mQuoteArrayLen;
	private Z_ListViewModel model;

	private WebView mWebView;

	private int cur_idx;

	public String[] urlArray;

	int getShownIndex() {
		return mCurrIdx;
	}

	// Show the Quote string at position newIndex
	void showQuoteAtIndex(int newIndex) {
		if (newIndex < 0 || newIndex >= mQuoteArrayLen)
			return;
		mCurrIdx = newIndex;
		mQuoteView.setText(B_Activity.mWebsiteArray2[mCurrIdx]);
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

	// Called to create the content view for this Fragment
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.descriptions2, container, false);


		model = new ViewModelProvider(requireActivity()).get(Z_ListViewModel.class);

		// retains last quote shown on config change
		model.getSelectedItem().observe(getViewLifecycleOwner(), item -> {

			mCurrIdx = item;

			if (MainActivity.selectedActivity == 1)
			{
				urlArray = getResources().getStringArray(R.array.Attraction_Description);
			}
			else
			{
				urlArray = getResources().getStringArray(R.array.Hotel_Description);
			}

			mWebView = view.findViewById(R.id.url_hotels);
			mWebView.setWebViewClient(new WebViewClient());
			mWebView.loadUrl(urlArray[mCurrIdx]);

		});

		return view;



	}

	@Override
	public void onViewCreated (View view, Bundle savedInstanceState){

		Log.i(TAG, getClass().getSimpleName() + ":entered onViewCreated()");
		super.onViewCreated(view,savedInstanceState);

	}



}
