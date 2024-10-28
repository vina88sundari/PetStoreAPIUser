package api.endpoints;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import api.payload.userpayload;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UserEndPoints {
	
	

public static Response createuser(userpayload payload)

{

	Response res =given().contentType("application/json").accept("application/json")
	.body(payload).log().all()
	.when().post(Routes.createuser);
	
	return res;


}


public static Response updateuser(userpayload payload,String uname)

{

	Response res =given().contentType("application/json").accept("application/json").pathParam("username", uname)
	.body(payload).log().all()
	.when().put(Routes.updateuser);
	
	return res;


}

public static Response getser(String uname)

{

	Response res =given().contentType("application/json").accept("application/json").pathParam("username", uname).log().all()
	.when().get(Routes.getuser);
	
	return res;


}
public static Response deleteuser(String uname)

{

	Response res =given().contentType("application/json").accept("application/json").pathParam("username", uname)
	.when().get(Routes.delete);
	
	return res;


}

}
