package com.tcc.printmobile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends ActionBarActivity {

	private final Activity mActivity = this;
	private static final int IMAGE_REQUEST = 0;
	private static final int PDF_REQUEST = 1;
	private Toolbar mToobar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btPDF = (Button) findViewById(R.id.btPDF);
		Button btIMG = (Button) findViewById(R.id.btIMG);

		mToobar = (Toolbar) findViewById(R.id.tb_main);
		mToobar.setTitle("Print Mobile");
		mToobar.setLogo(R.mipmap.icon);
		setSupportActionBar(mToobar);

		btPDF.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("application/pdf");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				startActivityForResult(intent, PDF_REQUEST);
			}
		});

		btIMG.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				startActivityForResult(intent, IMAGE_REQUEST);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
			Log.d("imagem teste", data.toString());

			Intent intent = new Intent(getApplicationContext(), PrintConfig.class);
			Bundle params = new Bundle();
			try {
				params.putByteArray("image", getBytesFromBitmap(data.getData()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			intent.putExtras(params);
			startActivity(intent);

		} else if(resultCode == Activity.RESULT_OK) {
			Log.d("pdf teste", data.toString());//here i am not sure if content here is a pdf or another file

			Intent intent = new Intent(getApplicationContext(), PrintConfig.class);
			Bundle params = new Bundle();
			try {
				params.putByteArray("pdf", getBytesToPdf(data.getData()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			intent.putExtras(params);
			startActivity(intent);
		}
	}

	public byte[] getBytesFromBitmap(Uri uri) throws IOException {
		InputStream is = getContentResolver().openInputStream(uri);
		Log.d("input_stream", is.toString());
		Bitmap bitmap = BitmapFactory.decodeStream(is);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);

		return stream.toByteArray();
	}

	public byte[] getBytesToPdf(Uri uri) throws IOException {
		InputStream is = getContentResolver().openInputStream(uri);
		Log.d("input_stream", is.toString());
		return IOUtils.toByteArray(is);
//		// this dynamically extends to take the bytes you read
//		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
//
//		// this is storage overwritten on each iteration with bytes
//		int bufferSize = 1024;
//		byte[] buffer = new byte[bufferSize];
//
//		// we need to know how may bytes were read to write them to the byteBuffer
//		int len = 0;
//		while ((len = is.read(buffer)) != -1) {
//			byteBuffer.write(buffer, 0, len);
//		}
//
//		// and then we can return your byte array.
//		return byteBuffer.toByteArray();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
