package com.ToJson;
import org.json.*;  

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;  
import java.io.IOException; 
public class XmlToJson {
	
	public static void main(String args[]) {
			String xml="";
			try{
				Integer count = 0;
			      File myObj = new File("SampleXml.xml");
			      Scanner myReader = new Scanner(myObj);
			      while (myReader.hasNextLine()) {
			        String Wxml = myReader.nextLine();
			        xml +=Wxml;
			        count++;
			        System.out.println(count);
			      }myReader.close();
				
				
				
				
				try {  
			JSONObject json = XML.toJSONObject(xml);   
			String jsonString = json.toString(4);  
			System.out.println(jsonString);  
			try {
			      FileWriter myWriter = new FileWriter("xmlnew.html");
			      myWriter.write(jsonString);
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			  
			}catch (JSONException e) {  
			// TODO: handle exception  
			System.out.println(e.toString());  
			e.printStackTrace();
			}  }catch(FileNotFoundException e) {
				System.out.println("An error occurred.");
			      e.printStackTrace();
			}
	}

}
