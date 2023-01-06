package com.example.recyclerview;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> myList;
    ArrayList<Integer> myList2;

    private ArrayList<Integer> pictures = new ArrayList<Integer>(
            Arrays.asList(R.drawable.image1, R.drawable.image2,
                    R.drawable.image3, R.drawable.image4, R.drawable.image5,
                    R.drawable.image6));

    private ArrayList<String> nameList = new ArrayList<String>(
            Arrays.asList("Bear", "Cheetah", "Crocodile", "Elephant", "Giraffe", "Lion"));

    private ArrayList<String> websites = new ArrayList<String>(
            Arrays.asList("https://en.wikipedia.org/wiki/Bear",
                    "https://en.wikipedia.org/wiki/Cheetah",
                    "https://en.wikipedia.org/wiki/Crocodile",
                    "https://en.wikipedia.org/wiki/Elephant",
                    "https://en.wikipedia.org/wiki/Giraffe",
                    "https://en.wikipedia.org/wiki/Lion"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        RecyclerView nameView = (RecyclerView) findViewById(R.id.recycler_view);

        ArrayList<String> names = new ArrayList<String>(
                Arrays.asList("Bear", "Cheetah", "Crocodile", "Elephant", "Giraffe", "Lion"));



        myList = new ArrayList<>();
        myList.addAll(names);

        myList2 = new ArrayList<>();
        myList2.addAll(pictures);



        //Define the listener with a lambda and access the list of names with the position passed in
        // RVClickListener listener = (view, position)-> Toast.makeText(this, "position: "+position, Toast.LENGTH_LONG).show();

        //Define the listener with a lambda and access the name of the list item from the view
        RVClickListener listener = (view, position) -> {

            Uri uri = Uri.parse(websites.get(position)); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

//            TextView name = (TextView) view.findViewById(R.id.textView);
//            Toast.makeText(this, name.getText(), Toast.LENGTH_SHORT).show();
        };

//        RVLongClickListener listener2 = (view, position) -> {
//
//            TextView name = (TextView) view.findViewById(R.id.textView);
//            Toast.makeText(this, name.getText(), Toast.LENGTH_SHORT).show();
//
//        };

        MyAdapter adapter = new MyAdapter(myList, myList2, listener);
        nameView.setHasFixedSize(true);
        nameView.setAdapter(adapter);
        nameView.setLayoutManager(new GridLayoutManager(this,3)); //use this line to see as a grid
        // nameView.setLayoutManager(new LinearLayoutManager(this)); //use this line to see as a standard vertical list


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()) {

            case R.id.list:
                Toast.makeText(this,"List selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.grid:
                Toast.makeText(this,"Already on grid Display", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);



        }
    }

/*  //you can put the contextItem selected method here or use a listener in the ViewHolder class
    @Override
    public boolean onContextItemSelected(MenuItem item){
        Log.i("ON_CLICK","menu item clicked");

        return true;
    }
    */

}