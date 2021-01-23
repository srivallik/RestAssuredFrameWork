package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public static Properties prop;
	
	public static void init() {
		
		prop =new Properties();
		try {
			FileInputStream fip= new FileInputStream("C:\\Users\\DELL\\CoreJavaWorkSpace\\RestAssuredFrameWork\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
