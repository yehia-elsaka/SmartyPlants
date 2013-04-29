package com.example.smartyplants;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartyplants.R;

@SuppressLint("NewApi")
public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		
		
		final Button settings = (Button) findViewById(R.id.settings);
		settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	
            	registerForContextMenu(v); 
                openContextMenu(v);
                unregisterForContextMenu(v);
            }
        });
		
		
		
		
		final ImageView sign_in = (ImageView) findViewById(R.id.sign_in);
		sign_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            	
            	Intent intent = new Intent(getApplicationContext(), Home.class);
    			startActivity(intent); 
            }
        });
		
		
	}
	
	
	
	 @Override  
	   public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		    
			menu.setHeaderTitle("Settings ... ");  
		    menu.add(0, v.getId(), 0, "Register");  
		    menu.add(0, v.getId(), 0, "App Info");  
		    menu.add(0, v.getId(), 0, "Quit"); 
	}  
	
	
	 @Override  
	 public boolean onContextItemSelected(MenuItem item) {  
	         if(item.getTitle()=="Register")
	         	{
	        	 	Regist_fun(item.getItemId());
	        	} 
	         
	         else if(item.getTitle()=="Quit")
	         	{
	        	 	this.finish();
	        	} 
	     
	         
	         return true;  
	 }  
	 
	 
	 public void Regist_fun(int id){  
		 
		 	Intent intent = new Intent(getApplicationContext(), Register.class);
			startActivity(intent); 
	    } 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
