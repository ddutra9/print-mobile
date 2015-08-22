package com.tcc.printmobile.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class ByteArray {
	public static byte[] convertPDFToByteArray(String path) {
		byte[] byteArray = null;
		try {
			InputStream inputStream = new FileInputStream(path);
			byteArray = IOUtils.toByteArray(inputStream);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArray;
	}
}
