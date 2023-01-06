package com.example.myapplication2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        switch(intent.getAction())
        {
            case "cs478.edu.project3_a1_attractions":
                MainActivity.selectedItem = false;
                MainActivity.selectedActivity = 1;
                Intent i1 = new Intent(context, A_Activity.class);
                context.startActivity(i1);
                break;
            case "cs478.edu.project3_a1_hotels":
                MainActivity.selectedItem = false;
                MainActivity.selectedActivity = 2;
                Intent i2 = new Intent(context, B_Activity.class);
                context.startActivity(i2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + intent.getAction());
        }

    }
}