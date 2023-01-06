package com.example.myapplication2;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    public static boolean selectedItem;
    public static int selectedActivity;
    private MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectedItem = false;
        selectedActivity = 1;

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("cs478.edu.project3_a1_attractions");
        filter.addAction("cs478.edu.project3_a1_hotels");
        registerReceiver(receiver, filter, "edu.uic.cs478.fall22.mp3", null);
    }

    @Override
    protected void onStop()
    {
        //unregisterReceiver(receiver);
        super.onStop();
    }
}

