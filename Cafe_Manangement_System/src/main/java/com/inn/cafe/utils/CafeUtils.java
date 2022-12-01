package com.inn.cafe.utils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class CafeUtils {

	private CafeUtils() {
		
	}
	
	public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
		return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
	}
	
	public static String getUUID() {
		Date date = new Date();
		long time = date.getTime();
		return "Bill-"+time;
	}
	
	public static JSONArray getJsonArrayFromString(String data) throws JSONException{
		System.out.println("Inside getJsonArrayFromString method");
		JSONArray jsonArray = new JSONArray(data);
		return jsonArray;
	}
	
	public static Map<String, Object> getMapFromJson(String data){
		System.out.println("Inside getMapFromJson method");
		if(!Strings.isNullOrEmpty(data)) {
			return new Gson().fromJson(data, new TypeToken<Map<String, Object>>() {
			}.getType());
		}
		return new HashMap<>();
	}
	
	public static boolean isFileExist(String path) {
		System.out.println("Inside isFileExist method");
		try {
			File file = new File(path);
			return (file!=null && file.exists()) ? Boolean.TRUE : Boolean.FALSE;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
