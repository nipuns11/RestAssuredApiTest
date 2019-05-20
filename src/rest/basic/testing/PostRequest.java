package rest.basic.testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.RestAssured;


public class PostRequest {

	@Test
	public void ValidatePostResp() {
		RestAssured.baseURI = "https://reqres.in";
		String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
		
		// extract the response json code 
		Response res = given().body(requestBody).when()
				.post("/api/users").then().assertThat().statusCode(201)
				.extract().response();
		String response = res.asString();
		System.out.println(response);
		JsonPath jsonResponse = new JsonPath(response);
		String pid =  jsonResponse.get("id");
		System.out.println(pid);
		String date = jsonResponse.getString("createdAt");
		System.out.print(date);
	
		// delete request
		given().body("{\"id\":\"" + pid + "\",\"createdAt\":\"" + date + "\"}").when().post("/api/users").then()
				.assertThat().statusCode(201).and().body("status", equalTo("Created"));

		}

}
