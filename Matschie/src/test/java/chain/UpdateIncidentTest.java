package chain;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class UpdateIncidentTest extends TestNGSetup {
	
	@Test(dependsOnMethods = "chain.CreateIncidentTest.sendPostRequest")
	public void sendUpdateRequest() {
		inputRequest
		.queryParam("sysparm_fields", "sys_id, description, category, number")
		.contentType(ContentType.JSON)
		.body("{\"description\":\"The issue is not from Oracle DBMS. The connectivity needs to be fixed\",\"category\":\"hardware\"}");
		
		response = inputRequest
				.when()
				.patch(sys_id);
		response.then().assertThat().statusCode(200);
	}

}
