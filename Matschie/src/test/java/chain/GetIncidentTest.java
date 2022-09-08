package chain;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetIncidentTest extends TestNGSetup {

	@Test
	public void sendGetRequest() {
		
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("sysparm_fields", "sys_id, description, category, number");
		queryMap.put("category", "software");
		
		inputRequest
		.queryParams(queryMap)
		.accept(ContentType.JSON);
		
		response = inputRequest.get();
		response.then().assertThat().statusCode(200);
		
		//Index known but value is partially known
		response.then().assertThat().body("result[0].number", 
				Matchers.containsString("INC"));
		
		//Value is known but not the index
		response.then().assertThat().body("result.sys_id", 
				Matchers.hasItem("5f395f0e97a1511097a438271153af28"));
		
		//value is known and also the index
		response.then().assertThat().body("result[0].sys_id", 
				Matchers.equalTo("8d6246c7c0a80164012fb063cecd4ace"));
		
		
	}
	
}
