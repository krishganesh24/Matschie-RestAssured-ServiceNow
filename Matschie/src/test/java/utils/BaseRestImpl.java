package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRestImpl {

	public static RequestSpecification inputRequest;
	public static String sys_id;
	public static Response response;

	public static void preConfig() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));
		RestAssured.baseURI = "https://" + prop.getProperty("server") + "/" + prop.getProperty("resources") + "/"
				+ prop.getProperty("table_name");
		inputRequest = RestAssured.given().log().all().auth().basic(prop.getProperty("username"),
				prop.getProperty("password"));
	}

	public static void postConfig() {
		response.then().log().all();
	}

}
