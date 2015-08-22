package com.tcc.printmobile.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.tcc.printmobile.model.File;
import com.tcc.printmobile.model.Img;
import com.tcc.printmobile.model.Pdf;

public class PostFile {
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

	public void postData(File file) {
		try {
			// Add your data
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("colorful", file
					.getColorful().toString()));
			nameValuePairs.add(new BasicNameValuePair("landscape", file
					.getLandscape().toString()));
			nameValuePairs.add(new BasicNameValuePair("copies", file
					.getCopies().toString()));

			if (file instanceof Img)
				addBody((Img) file, nameValuePairs);
			else
				addBody((Pdf) file, nameValuePairs);

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	private void addBody(Img img, List<NameValuePair> nameValuePairs) {
	}

	private void addBody(Pdf pdf, List<NameValuePair> nameValuePairs) {
		nameValuePairs.add(new BasicNameValuePair("intervalPage", pdf
				.getColorful().toString()));
	}
}
