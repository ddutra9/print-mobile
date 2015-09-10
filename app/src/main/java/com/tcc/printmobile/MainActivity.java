package com.tcc.printmobile;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private final Activity mActivity = this;

	private Toolbar mToobar;

	// private final Context mContext = this;

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
				File mPath = new File(Environment.getExternalStorageDirectory().toString());
				FileDialog fileDialog = new FileDialog(mActivity, mPath);
				fileDialog.setFileEndsWith(".pdf");
				fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
						public void fileSelected(File file) {
							Log.d(getClass().getName(), "Arquivo selecionado: "+ file.toString());

							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setDataAndType(Uri.fromFile(file), "application/pdf");
							intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
							startActivity(intent);


						}
				});

				// show the FileDialog
				fileDialog.showDialog();
			}
		});


		btIMG.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				File mPath = new File(Environment.getExternalStorageDirectory().toString());
				FileDialog fileDialog = new FileDialog(mActivity, mPath);
				fileDialog.setFileEndsWith(".jpg");
				fileDialog.addFileListener(new FileDialog.FileSelectedListener() {
						public void fileSelected(File file) {
							Log.d(getClass().getName(), "Arquivo selecionado: "+ file.toString());

							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setDataAndType(Uri.fromFile(file), "image/jpeg");
							intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
							startActivity(intent);

						}
				});

				// show the FIle
				fileDialog.showDialog();


			}
		});

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
