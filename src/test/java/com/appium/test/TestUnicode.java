package com.appium.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.testng.annotations.Test;

public class TestUnicode {
	@Test
	public void set() throws UnsupportedEncodingException{
	String a= "Tôi là thế";
	
	String utfString = new String(a.getBytes(Charset.forName("utf-8")));
	System.out.println(utfString);
	
	String countryString = "المملكة العربية السعودية";
    String utfCountryString = new String(countryString.getBytes(), "utf-8");
    System.out.println("UTF String : "+utfCountryString);
    System.out.println("Original String : "+countryString);
}
}
