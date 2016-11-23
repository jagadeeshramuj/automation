package main.travelhopesPages;

import com.jayway.restassured.response.Response;
import org.testng.Assert;

import com.jayway.restassured.RestAssured;
import  com.jayway.restassured.RestAssured.*;
import org.testng.annotations.Test;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import com.jayway.restassured.*;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
public class TravelHopesApiPages {
	static JSONParser parser=new JSONParser();
	static JSONArray jsonobjects;
	 public static void loadJsonObjects() {
		 try {
			 jsonobjects = (JSONArray) parser.parse(new FileReader("res/APIProperties"));
			 System.out.println(jsonobjects.toString());

		    } catch (FileNotFoundException e) {
		    	e.printStackTrace();
		    } catch (IOException e) {

		    } catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }

	@Test(enabled=true)
	public void gethotels(){
		//Response resp= get("http://beanstalk-test.travelprologavailabilityue.com/msf/api/v2/");
		//Assert.assertEquals(200,resp.getStatusCode());
		this.loadJsonObjects();
		
		System.out.println("The JSon Object is :"+jsonobjects.get(0).toString());
		RestAssured.baseURI  = "http://beanstalk-test.travelprologue.com/msf/api/v2/availability";
		
		Response r = given()
		    	.contentType("application/json").
		    	body(jsonobjects.get(0)).
		        when().
		        post("");
		String body = r.getBody().asString();
    	System.out.println("The Body is :"+body);
    	JSONObject object= new JSONObject(body);
    	System.out.println("Response Date:"+object.get("responseDate"));
    	System.out.println("Location:"+object.getJSONObject("location"));
    	
	}
}
