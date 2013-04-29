package com.example.smartyplants;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;





@SuppressWarnings("deprecation")
public class Home extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        
        
        TabHost tabHost = getTabHost();
        
        // Tab for Id_plants
        TabSpec id_plants = tabHost.newTabSpec("ID_Plants");
        // setting Title and Icon for the Tab
        id_plants.setIndicator("ID Plants");
        Intent photosIntent = new Intent(this, ID_PlantsActivity.class);
        id_plants.setContent(photosIntent);
         
        // Tab for My_Plants
        TabSpec my_plants = tabHost.newTabSpec("My_Plants");    
        my_plants.setIndicator("My Plants");
        Intent songsIntent = new Intent(this, My_PlantsActivity.class);
        my_plants.setContent(songsIntent);
         
        // Tab for Submit_Plants
        TabSpec submit_plants = tabHost.newTabSpec("Submit_Plants");
        submit_plants.setIndicator("Submit Plant");
        Intent videosIntent = new Intent(this, Submit_PlantsActivity.class);
        submit_plants.setContent(videosIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(id_plants); // Adding photos tab
        tabHost.addTab(my_plants); // Adding songs tab
        tabHost.addTab(submit_plants); // Adding videos tab
        
        
        // change tabs font's size
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(10);
        }

        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
    
}
