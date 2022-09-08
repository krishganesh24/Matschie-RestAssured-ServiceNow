package steps;

import java.io.File;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import utils.BaseRestImpl;

public class IncidentManagementStepsImpl extends BaseRestImpl {

	@And("user set the queryparam in the Incident management request")
	public void setQueryParam(DataTable dt) {
		//inputRequest.queryParams(dt.asMap());
	}

	@And("user set the request header as {string} in the Incident management request")
	public void setReqHeader(String contentType) {
		if (contentType.equalsIgnoreCase("JSON")) {
			inputRequest.contentType(ContentType.JSON).accept(ContentType.JSON);
		} else if (contentType.equalsIgnoreCase("XML")) {
			inputRequest.contentType(ContentType.XML).accept(ContentType.JSON);
		}
	}

	@And("user set the request body filename as {string} in the Incident management request")
	public void setRequestBody(String fileName) {
		File inputFile = new File("./data/" + fileName);
		inputRequest.body(inputFile);
	}

	@When("user sends a Post request to the service now application")
	public void sendPostRequest() {
		response = inputRequest.when().post();
	}

	@When("user sends a Get request to the service now application")
	public void sendGetRequest() {
		response = inputRequest.when().get();
	}

	@Then("Verify the status code {int} for the Incident management response")
	public void verifyStatusCode(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@Then("Validate the sys_id in the Incident management response")
	public void validateSysID() {
		String sys_id = response.jsonPath().get("result.sys_id");
		System.out.println("Sys_ID for the request: " + sys_id);
	}

}
