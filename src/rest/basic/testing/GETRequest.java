package rest.basic.testing;

import io.restassured.*;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GETRequest {

	// base url

	public static String baseURL = "https://maps.googleapis.com";

	// simple get request

	public static void main(String args[]) {
		RestAssured.baseURI = baseURL;

		given().param("location", "-33.8670522,151.1957362").param("radius", "500").param("type", "restaurant")
				.param("key", "AIzaSyDlgGOD2G3XhgT6iI_7HYT517rhPoJPOvM").when().get("maps/api/place/nearbysearch/json")
				.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("results[0].name", equalTo("The Little Snail Restaurant")).and()
				.body("results[13].name", equalTo("Harvest Buffet at The Star Sydney")).and()
				.body("results[13].vicinity", equalTo("80 Pyrmont Street, Pyrmont")).and()
				.header("Server", "scaffolding");// verification fo header

	}

}
