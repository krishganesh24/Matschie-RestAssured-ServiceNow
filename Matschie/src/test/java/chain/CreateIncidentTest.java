package chain;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CreateIncidentTest extends TestNGSetup {
	
	@Test(dataProvider = "fetchData")
	public void sendPostRequest(String fileName, String contentType) {
		File inputFile = new File("./data/"+fileName);
		String reqType = null;
		
		if(contentType.equalsIgnoreCase("JSON")) {
			reqType = "application/json";
		}else if(contentType.equalsIgnoreCase("XML")) {
			reqType = "application/xml";
		}
		inputRequest
				.queryParam("sysparm_fields", "sys_id, description, category, number")
				.contentType(reqType)
				.accept(ContentType.JSON)
				.body(inputFile);
		
		response = inputRequest.post();
		response.then().assertThat().statusCode(201);
		sys_id = response.jsonPath().get("result.sys_id");
		System.out.println("Sys_ID: "+sys_id);				
	}
	
	@DataProvider(name = "fetchData")
	public String[][] dataProvider(){
		String[][] data = new String[2][2];
		data[0][0] = "CreateIncident.json";
		data[0][1] = "JSON";
		
		data[1][0] = "CreateIncident.xml";
		data[1][1] = "XML";
		return data;
	}
}
