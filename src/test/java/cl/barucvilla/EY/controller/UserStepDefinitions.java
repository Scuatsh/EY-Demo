package cl.barucvilla.EY.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import cl.barucvilla.EY.ScenarioContext;
import cl.barucvilla.EY.SpringIntegrationTest;
import cl.barucvilla.EY.entity.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserStepDefinitions extends SpringIntegrationTest {

	private Response response;
	private User userData = new User();
	private String lastId;

	private ScenarioContext scenarioContext;

	public void setUp(ScenarioContext scenario) {
		this.scenarioContext = scenario;
	}

	@Given("Have a user data:")
	public void have_a_user_data(DataTable dataTable) {
		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : data) {
			userData.setName(row.get("name"));
			userData.setEmail(row.get("email"));
			userData.setPassword(row.get("password"));
		}
	}

	@When("send POST to {string} with data user")
	public void send_post_to_with_data_user(String string) {
		this.response = RestAssured.given().contentType("application/json").body(userData).post(string);
		System.out.println("Response: " + this.response.asString());
		lastId = this.response.getBody().jsonPath().getString("id");
		scenarioContext.setContext("lastId", lastId);

	}

	@Then("wait {int} code to response")
	public void wait_code_to_response(Integer int1) {
		assertEquals(int1, this.response.getStatusCode());
	}

	@When("send PUT to {string} with data id {string}")
	public void send_put_to_with_data_id(String string, String string2) {
		String id = scenarioContext.getContext("lastId").toString();
		System.out.println("Last ID: " + id);
		this.response = RestAssured.given().contentType("application/json").body(userData).put(string2 + "/" + id);
	}
}
