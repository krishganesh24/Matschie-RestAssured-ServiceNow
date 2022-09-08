package chain;

import org.testng.annotations.Test;

public class DeleteIncidentTest extends TestNGSetup {
	
	@Test(dependsOnMethods = "chain.UpdateIncidentTest.sendUpdateRequest")
	public void sendDeleteRequest() {	
		response = inputRequest
				.when()
				.delete(sys_id);
		response.then().assertThat().statusCode(204);
	}

}
