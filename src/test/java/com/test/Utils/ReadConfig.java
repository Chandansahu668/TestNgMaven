package com.test.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties prop;
	public ReadConfig() {
		try {
			File src = new File ("./Resource/config.properties");
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String getbaseUrl() {
		String baseUrl = prop.getProperty("DesktopUrl");
		return baseUrl;
	}
	
	public String getUserName() {
		String userName = prop.getProperty("UserName");
		return userName;
	}
	public String getPassword() {
		String password = prop.getProperty("Password");
		return password;
	}
}
