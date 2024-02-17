package api.endpoints;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import api.payload.User;

//Created for CRUD operation

public class UserEndPointsusing_routes {

	public static Response createUser(User payload){

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(routes.post_url);

		return response;
	}

	public static Response getUser(String userName){

		Response response = given()
				.pathParam("username", userName)

				.when()
				.get(routes.get_url);

		return response;
	}

	public static Response updateUser(String userName,User payload){

		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", userName)

				.when()
				.put(routes.update_url);

		return response;
	}
	
	public static Response deleteUser(String userName){

		Response response = given()
				.pathParam("username", userName)

				.when()
				.delete(routes.delete_url);

		return response;
	}


}
