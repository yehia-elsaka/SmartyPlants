package smartyplant.core;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.widget.Gallery;
import android.widget.GridView;

public class GalleryView extends Activity{
	Gallery gallery;
	Context mContext = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);
		gallery= (Gallery) findViewById(R.id.grid_view);
		gallery.setAdapter(new GalleryAdapter(mContext, getColumnWidth(),getColumnHeight()));
		
		
	}
	
	private int getColumnWidth(){
		Display display = getWindowManager().getDefaultDisplay();
		int screenWidth = display.getWidth();
		int colWidth = (screenWidth-50) / 3;
		return colWidth;
	}
	
	private int getColumnHeight(){
		Display display = getWindowManager().getDefaultDisplay();
		int screenHeight = display.getHeight();
		int colHeight = (screenHeight-30) / 3;
		return colHeight;
	}
	

}
