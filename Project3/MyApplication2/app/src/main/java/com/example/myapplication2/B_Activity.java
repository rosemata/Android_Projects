package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class B_Activity extends AppCompatActivity {

    public static String[] mTitleArray2;
    public static String[] mWebsiteArray2;

    private final B_Fragment2 mQuoteFragment = new B_Fragment2();

    FragmentManager mFragmentManager2;

    private FrameLayout mTitleFrameLayout2, mQuotesFrameLayout2;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "QuoteViewerActivity";

    private Z_ListViewModel mModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ": entered onCreate()");

        super.onCreate(savedInstanceState);

        // Get the string arrays with the titles and qutoes
        mTitleArray2 = getResources().getStringArray(R.array.Hotel_Titles);
        mWebsiteArray2 = getResources().getStringArray(R.array.Hotel_Description);

        setContentView(R.layout.hotels);

        // Get references to the TitleFragment and to the QuotesFragment
        mTitleFrameLayout2 = (FrameLayout) findViewById(R.id.title_fragment_container2);
        mQuotesFrameLayout2 = (FrameLayout) findViewById(R.id.quote_fragment_container2);

        // Get a reference to the SupportFragmentManager instead of original FragmentManager
        mFragmentManager2 = getSupportFragmentManager();

        // Start a new FragmentTransaction
        final FragmentTransaction fragmentTransaction2 = mFragmentManager2.beginTransaction();

        // Add the TitleFragment to the layout
        // UB: 10/2/2016 Changed add() to replace() to avoid overlapping fragments
        fragmentTransaction2.replace(
                R.id.title_fragment_container2,
                new B_Fragment1());

        // Commit the FragmentTransaction
        fragmentTransaction2.commit();

        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager2.addOnBackStackChangedListener(
                // UB 2/24/2019 -- Use support version of Listener
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });

        // set up model observer to add and remove quotes fragment
        // Note: the fragment object never gets deleted, it just gets removed when the user
        // presses the "back" button
        mModel = new ViewModelProvider(this).get(Z_ListViewModel.class) ;
        mModel.getSelectedItem().observe(this, item -> {
            if (!mQuoteFragment.isAdded()) {
                FragmentTransaction fragmentTransaction4 = mFragmentManager2.beginTransaction() ;

                // add quote fragment to display
                fragmentTransaction4.add(R.id.quote_fragment_container2,
                        mQuoteFragment);

                // Add this FragmentTransaction to the backstack
                fragmentTransaction4.addToBackStack(null);

                // Commit the FragmentTransaction
                fragmentTransaction4.commit();

                // Force Android to execute the committed FragmentTransaction
                mFragmentManager2.executePendingTransactions();
            }
        });
        setLayout() ;
    }
    private void setLayout() {

        // Determine whether the QuoteFragment has been added
        if (!mQuoteFragment.isAdded()) {

            // Make the TitleFragment occupy the entire layout
            mTitleFrameLayout2.setLayoutParams(new LinearLayout.LayoutParams(
                    MATCH_PARENT, MATCH_PARENT));
            mQuotesFrameLayout2.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        } else {


            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // In landscape

                // Make the TitleLayout take 1/3 of the layout's width
                mTitleFrameLayout2.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the QuoteLayout take 2/3's of the layout's width
                mQuotesFrameLayout2.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 3f));
            } else {
                // In portrait


                // Make the TitleLayout take 1/3 of the layout's width
                mTitleFrameLayout2.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 0f));

                // Make the QuoteLayout take 2/3's of the layout's width
                mQuotesFrameLayout2.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.attractions_option:
                Toast.makeText(this, "Hotels Selected", Toast.LENGTH_SHORT);
            case R.id.hotels_option:
                Intent intent = new Intent(this, A_Activity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

