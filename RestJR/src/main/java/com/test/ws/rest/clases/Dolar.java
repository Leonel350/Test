package com.test.ws.rest.clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;

public class Dolar implements Comportamiento {

    public String compra(){
    	try {
    		String precio = readJsonFromUrl("https://www.bancoprovincia.com.ar/Principal/Dolar");
    		JSONArray nuevoArray=new JSONArray(precio);
    		return nuevoArray.getString(0);
    		
    	} catch (IOException e) {
			return "-1";
		}
    }
    public  String venta(){
    	try {
    		String precio = readJsonFromUrl("https://www.bancoprovincia.com.ar/Principal/Dolar");
    		JSONArray nuevoArray=new JSONArray(precio);
    		return nuevoArray.getString(1);
    		
    	} catch (IOException e) {
			return "-1";
		}
    }
    
    private static String readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          return jsonText;
        } finally {
          is.close();
        }
      }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
}
