package api.test;

import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.userpayload;
import api.utilities.Dataprovider;
import io.restassured.response.Response;

public class dataprovidertest {
	
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=Dataprovider.class)
	public void postrequestdata(String userid,String username,String fname,String lname,String uemail,String pwd,String phone)
	
	{
		userpayload up =new userpayload();
		
		up.setId(Integer.parseInt(userid));
		up.setUsername(username);
		up.setFirstName(fname);
		up.setLastName(lname);
		up.setEmail(uemail);
		up.setPassword(pwd);
		up.setPhone(phone);
		
		Response res =UserEndPoints.createuser(up);
		res.then().log().all();
	}

}
