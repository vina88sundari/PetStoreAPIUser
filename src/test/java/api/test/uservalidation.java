package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.google.gson.JsonObject;

import api.endpoints.UserEndPoints;
import api.payload.userpayload;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class uservalidation {
	
	userpayload payload=new userpayload();
	
	Faker fk =new Faker();
	
	public Logger logger;
	
	@BeforeClass
	void setuserdata()
	
	{
		payload.setId(fk.idNumber().hashCode());
		payload.setUsername(fk.name().username());
		payload.setFirstName(fk.name().firstName());
		payload.setLastName(fk.name().lastName());
		payload.setEmail(fk.internet().emailAddress());
		payload.setPassword(fk.internet().password(6, 15));
		payload.setPhone(fk.phoneNumber().cellPhone());
		payload.setUserStatus(0);
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test
	void createusercheck()
	
	{
		
		logger.info("************ Creating User *********************");
		Response res =UserEndPoints.createuser(payload);
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("************User is created *********************");
	}
	
	@Test
	void getusercheck()
	
	{
		Response res =UserEndPoints.getser(payload.getUsername());
		
		res.then().log().all();
		
		
	}
	@Test
	void updateusercheck()
	
	{
		
		payload.setEmail(fk.internet().emailAddress());
		payload.setPhone(fk.phoneNumber().cellPhone());
		
		String updateemail=payload.getEmail();
		
		Response res =UserEndPoints.updateuser(payload, payload.getUsername());
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		//check after update
		Response resp =UserEndPoints.getser(payload.getUsername());
		
		JSONObject jo =new JSONObject(resp.asString());
		String emailcheck= jo.getString("email");
		Assert.assertEquals(updateemail, emailcheck);
		
		
	}
	
	@Test
	void deleteusercheck()
	
	{
		Response res =UserEndPoints.deleteuser(payload.getUsername());
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}


}
