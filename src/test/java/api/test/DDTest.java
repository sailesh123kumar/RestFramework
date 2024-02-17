package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;


public class DDTest {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userID,String Username,String firstname,String lastname,String useremail,String pwd,String phone) {
		
		User payload=new User();
		
		payload.setId(Integer.parseInt(userID));
		payload.setUsername(Username);
		payload.setFirstname(firstname);
		payload.setLastname(lastname);
		payload.setEmail(useremail);
		payload.setPassword(pwd);
		payload.setPhone(phone);
		
		
		Response res = UserEndPoints.createUser(payload);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority=2,dataProvider="Username",dataProviderClass = DataProviders.class)
	public void testDeleteUserbyName(String Username) {
		Response res = UserEndPoints.deleteUser(Username);	
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
