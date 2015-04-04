package com.sf.kits.string;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Stream {
	/**
	 * 
	 * @param string
	 * @return
	 */
	public static InputStream String2InputStream(String string){
		InputStream inputStream = new ByteArrayInputStream(string.getBytes());
		return inputStream;
	}
	/**
	 * 
	 * @param inputStream
	 * @return
	 */
	public static String InputStream2String(InputStream inputStream){
		ByteArrayOutputStream outputStream = null;
		try {
			outputStream = new ByteArrayOutputStream();
			int i;
			while((i=inputStream.read()) != -1){
				outputStream.write(i);
			}
			String string = outputStream.toString();
			return string;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param string
	 * @return
	 */
	public static OutputStream String2OutputStream(String string){
		OutputStream os = System.out;
		try {
			os.write(string.getBytes());
			return os;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static String OutputStream2String(OutputStream outputStream){
		String str = outputStream.toString();
		return str;
	}
}
