package com.example.smartyplants;

import com.example.smartyplants.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
 
public class My_PlantsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_plants_layout);
        
        
        
        GridView gridView = (GridView) findViewById(R.id.grid_view);

		// Instance of ImageAdapter Class
		gridView.setAdapter(new ImageAdapter(this));
    }
}