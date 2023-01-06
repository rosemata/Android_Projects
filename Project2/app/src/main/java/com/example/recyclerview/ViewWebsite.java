package com.example.recyclerview;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewWebsite extends Activity {
    private int position;
    //private String web;
    private ArrayList<String> websites = new ArrayList<String> (
            Arrays.asList("https://en.wikipedia.org/wiki/Bear",
                    "https://en.wikipedia.org/wiki/Cheetah",
                    "https://en.wikipedia.org/wiki/Crocodile",
                    "https://en.wikipedia.org/wiki/Elephant",
                    "https://en.wikipedia.org/wiki/Giraffe",
                    "https://en.wikipedia.org/wiki/Lion"));

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Uri uri = Uri.parse(websites.get(position));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

}
