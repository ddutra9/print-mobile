package com.tcc.printmobile.service;

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

public class PostFile {
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

	public void postData(File file) {
		try {
			JSONObject object = new JSONObject();

			try {
				object.put("colorful", file.getColorful());
				object.put("landscape", file.getLandscape());
				object.put("byteOfObj", file.getLandscape());
				object.put("copies", file.getLandscape());

				if(file instanceof Pdf)
					object.put("intervalPage", file.getLandscape());

			} catch (JSONException e) {
				e.printStackTrace();
			}

			httppost.setEntity(new StringEntity(object.toString(), "UTF8"));
			httppost.setHeader("Content-type", "application/json");

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			if (response != null) {
				if (response.getStatusLine().getStatusCode() == 200){



				}

			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
}
