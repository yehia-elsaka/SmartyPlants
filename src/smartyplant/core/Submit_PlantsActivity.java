package smartyplant.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import smartyplant.Utils.GlobalState;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Submit_PlantsActivity extends Activity {

	Context mContext = this;

	int mode = 1;
	String PhotoPath = Environment.getExternalStorageDirectory()
			+ "//smarty_plant.jpg";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setMode1();
		setClickListners();

	}

	protected void startCameraActivity() {
		File file = new File(PhotoPath);
		Uri outputFileUri = Uri.fromFile(file);
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		setMode1();
		switch (resultCode) {
		case 0:
			break;

		case -1:
			try {
				Drawable d = onPhotoTaken();
				TextView t = (TextView) findViewById(R.id.image_view);
				t.setBackgroundDrawable(d);
				Display display = getWindowManager().getDefaultDisplay();
				int width = display.getWidth() / 2;
				int height = display.getHeight() / 3;
				t.setLayoutParams(new RelativeLayout.LayoutParams(width, height));
				android.widget.RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) t
						.getLayoutParams();
				params.addRule(RelativeLayout.CENTER_HORIZONTAL);
				t.setLayoutParams(params);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected Drawable onPhotoTaken() throws Exception {

		
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		Bitmap bitmap = BitmapFactory.decodeFile(PhotoPath, options);
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float scaleWidth = ((float) 1) / 2;
		float scaleHeight = ((float) 1) / 2;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		String ff = Environment.getExternalStorageDirectory()
				+ "/compressed.png";
		try {
			FileOutputStream out = new FileOutputStream(ff);
			resizedBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			File queryImg = new File(ff);
			int imageLen = (int) queryImg.length();
			byte[] imgData = new byte[imageLen];
			FileInputStream fis = new FileInputStream(queryImg);
			fis.read(imgData);
			GlobalState.getInstance().base64 = Base64.encodeToString(imgData,Base64.DEFAULT);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Drawable d = new BitmapDrawable(resizedBitmap);
		GlobalState.getInstance().currentBitmap = resizedBitmap;
		return d;
	}

	private void setMode1() {
		setContentView(R.layout.submit_plants_layout);
		mode = 1;
		setClickListners();

	}

	private void setClickListners() {
		Button takePic = (Button) findViewById(R.id.take_picture);
		takePic.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startCameraActivity();
			}
		});

		Button gallery = (Button) findViewById(R.id.select_gallery);
		gallery.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				openGallery();
			}
		});

		Button done = (Button) findViewById(R.id.done);
		done.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(mContext, Submit.class));
			}
		});

	}

	private void openGallery() {
		mode = 2;
		setContentView(R.layout.gallery);
		setClickListners();
		GridView gridView = (GridView) findViewById(R.id.grid_view);
		gridView.setColumnWidth(getColumnWidth());
		gridView.setAdapter(new GalleryAdapter(mContext, getColumnWidth(),
				getColumnHeight()));
	}

	private int getColumnWidth() {
		Display display = getWindowManager().getDefaultDisplay();
		int screenWidth = display.getWidth();
		int colWidth = (screenWidth - 50) / 3;
		return colWidth;
	}

	private int getColumnHeight() {
		Display display = getWindowManager().getDefaultDisplay();
		int screenHeight = display.getHeight();
		int colHeight = (screenHeight - 30) / 3;
		return colHeight;
	}

	
}
