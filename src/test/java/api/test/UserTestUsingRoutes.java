package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPointsusing_routes;
import api.payload.User;

import io.restassured.response.Response;

public class UserTestUsingRoutes {
	
	Faker fake;
	User payload;
	public Logger log;
	
	@BeforeClass
	public void setupData() {
		
		fake = new Faker();
		payload = new User();
		
		
		
		payload.setId(fake.idNumber().hashCode());
		payload.setUsername(fake.name().username());
		payload.setFirstname(fake.name().firstName());
		payload.setLastname(fake.name().lastName());
		payload.setEmail(fake.internet().safeEmailAddress());
		payload.setPassword(fake.internet().password(5,10));
		payload.setPhone(fake.phoneNumber().cellPhone());
		
		//Logger class
		log=LogManager.getLogger(this.getClass());
		
	}
	
	
	@Test(priority=1)
	public void testPostUser(){
		
		Response res = UserEndPointsusing_routes.createUser(payload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	@Test(priority=2)
	public void testGetUserbyName() {
		
		log.info("**************Create User**************");
		Response res = UserEndPointsusing_routes.getUser(this.payload.getUsername());	
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		log.info("**************User is Created**************");
		
	}
	
	@Test(priority=3)
	public void testUpdateuserbyName() {
		
		log.info("**************Update User**************");
		//Update data
		payload.setFirstname(fake.name().firstName());
		payload.setLastname(fake.name().lastName());
		payload.setEmail(fake.internet().safeEmailAddress());
		
		//Updating the data
		Response res = UserEndPointsusing_routes.updateUser(this.payload.getUsername(),payload);
		res.then().log().body().statusCode(200);   //Chai assertion inbuilt from rest
		
		Assert.assertEquals(res.getStatusCode(), 200);   //TestNg assertion
		log.info("**************Getting Updated Info**************");
		//Getting the updated 
		Response res1 = UserEndPointsusing_routes.getUser(this.payload.getUsername());	
		res.then().log().all();
		
		Assert.assertEquals(res1.getStatusCode(), 200);
		log.info("**************User Updated**************");
	}
	
	@Test(priority=4)
	
	public void testDeleteUser() {
		log.info("**************Delete User**************");
		Response res = UserEndPointsusing_routes.deleteUser(this.payload.getUsername());	
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		log.info("**************User Deleted**************");
		
	}

}
