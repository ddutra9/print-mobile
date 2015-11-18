package com.tcc.printmobile.service;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.tcc.printmobile.model.File;
import com.tcc.printmobile.model.Pdf;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PostFile extends AsyncTask<File, Void, String> {
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httppost = null;

	@Override
	protected String doInBackground(File... params) {
		for (File file : params) {
			try {
				JSONObject object = new JSONObject();

				try {
					object.put("colorful", file.getColorful());
					object.put("landscape", file.getLandscape());
					object.put("copies", file.getCopies());
					Log.d("json_Object: ", object.toString());
					object.put("byteBase64", Base64.encodeToString(file.getByteOfObj(),
							Base64.NO_WRAP));

					Log.d("json_Object: ", object.get("byteBase64").toString());
					if(file instanceof Pdf) {
						object.put("intervalPage", file.getLandscape());
						httppost = new HttpPost("http://192.168.1.160:8080/printmobile-web/print/pdf");
					} else
						httppost = new HttpPost("http://192.168.1.160:8080/printmobile-web/print/image");

				} catch (JSONException e) {
					Log.d("Debug: ",e.toString());
					e.printStackTrace();
				}

				httppost.setEntity(new StringEntity(object.toString(), "UTF8"));
				httppost.setHeader("Accept", "application/json");
				httppost.setHeader("Content-type", "application/json");

				// Execute HTTP Post Request
				HttpResponse response = httpclient.execute(httppost);
				if (response != null) {
					if (response.getStatusLine().getStatusCode() != 200)
						return  "Server error: " + response.getStatusLine().getStatusCode();
				}

			} catch (ClientProtocolException e) {
				Log.d("Debug: ",e.toString());
				return e.getMessage().toString();
			} catch (IOException e) {
				Log.d("Debug: ",e.toString());
				return e.getMessage().toString();
			}
		}

		return null;
	}


}
