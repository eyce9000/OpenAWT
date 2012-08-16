package org.openawt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Image {
	private byte[] imageData;
	
	public static Image read(File file) throws IOException{
		return read(new FileInputStream(file));
	}
	public static Image read(InputStream inputStream) throws IOException{
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
		  buffer.write(data, 0, nRead);
		}

		buffer.flush();

		Image image = new Image();
		image.imageData = buffer.toByteArray();
		return image;
	}
}
