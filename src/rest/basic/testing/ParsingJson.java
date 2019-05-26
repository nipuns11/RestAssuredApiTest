package rest.basic.testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParsingJson {
	@Test
	public void ValidateResp() {
		RestAssured.baseURI = "https://reqres.in";
		//log.all for printing logs
		Response res = given().param("page", "2").log().all().when()
				.get("/api/users").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).extract().response();// verification fo header
		
		String response = res.asString();
		System.out.println(response);
		// parse the string in json format 
		JsonPath jsonResponse = new JsonPath(response);
		int arrSize = jsonResponse.getInt("data.size()");
		System.out.println(arrSize);
		// print the id values for data 
		for(int i=0 ; i < arrSize; i++)
		{
			String name = jsonResponse.getString("data["+i+"].id");
			System.out.println(name);
		}
		
		
		
	}
	
}
