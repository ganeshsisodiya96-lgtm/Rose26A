package com.skillio.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropUtil {

	public String getProperty(String filepath,String key) throws IOException{
		FileInputStream fis = new FileInputStream(filepath);
		String value=null;
		Properties prop = new Properties();
		prop.load(fis);
		value =prop.getProperty(key);
		return value;
	}
	public static void main(String[] args) throws IOException {
		String value=new PropUtil().getProperty("./src/main/resources/XOR.properties", "username");
		System.out.println("value: "+value);
	}
}