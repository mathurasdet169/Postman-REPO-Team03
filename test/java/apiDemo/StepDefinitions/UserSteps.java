package apiDemo.StepDefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.cucumber.java.en.Then;
import java.io.IOException;
import org.testng.Assert;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import apiDemo.endpoints.Routes;
import apiDemo.endpoints.UserCRUDFuntions;
import apiDemo.payload.UserDetails;

public class UserSteps {

	//private List<User> users;
	private static String Endpoint ; // Update this as necessary
	private static  String userId;
	private static String userFirstName;
	private static String JsonRequestBody;
	private static Response response;
	public static int exp_code;
	//String filePath;
	public static String payload_URL;


	@Given("User set request body from data file with scenario {string} and endpoint {string}")
	public void user_set_request_body_from_data_file_with_scenario_and_endpoint(String scenarioName, String endpoint) {

		
		
		// Read JSON data from the specified file
		UserDetailsJsonReader jsonReader=new UserDetailsJsonReader();
		UserDetails userobj = new UserDetails();

		try {
			userobj = jsonReader.user_json_Reader(Routes.filePath,scenarioName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonRequestBody = gson.toJson(userobj);
			payload_URL=Routes.base_URL+endpoint;
	
	}

	@When("user sends API post request")
	public void user_sends_api_post_request() {

		response=UserCRUDFuntions.create_user(JsonRequestBody, payload_URL);
		
			System.out.println(response.asPrettyString());
		System.out.println(response.getStatusCode());

		if(response.getStatusCode()==201)
		{
			userFirstName=response.getBody().jsonPath().getString("user_first_name");

			userId = response.getBody().jsonPath().getString("user_id");	
			int user_Id=(Integer.parseInt(userId));

			UserCRUDFuntions.setUSER_ID(user_Id);

			UserCRUDFuntions.setUSER_FIRST_NAME(userFirstName);
		}


	}


	@Then("response body and response code {int} is validated")
	public void response_body_and_response_code_is_validated(Integer exp_code) {

		Assert.assertEquals(response.getStatusCode(),exp_code );
		
		if(response.getStatusCode()==201) 
		{
		System.out.println("userID from UserCRUDFunctions : "+UserCRUDFuntions.getUSER_ID());
		System.out.println("userName from UserCRUDFunctions : "+UserCRUDFuntions.getUSER_FIRST_NAME());
		response.then()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaValidation.json"));//schema validation
		
		Assert.assertEquals(response.getContentType(), "application/json");//content-type validation
		
		
			
		}
	}

	//---------------------GET SCENARIOS-------------//
	
	//---GET ALL USERS----//

	@Given("User set GET request with endpoint {string}")
	public void user_set_get_request_with_endpoint(String endpoint) {

		//Endpoint=endpoint;
		payload_URL=Routes.base_URL+endpoint;
	}

	@When("User send GET request")
	public void user_send_get_request() {

		response=UserCRUDFuntions.get_allusers(payload_URL);

		System.out.println(response.asPrettyString());
		System.out.println(response.getStatusCode());

	}

	@Then("response should be validated for statuscode {int}, content-type")
	public void response_should_be_validated_for_statuscode_content_type(Integer code) {

		Assert.assertEquals(response.getStatusCode(), code);

		

	}

	//------------GETByUserName--------------
	@Given("User set GET BY USERNAME request for scenario {string} with endpoint {string}")
	public void user_set_get_by_username_request_for_scenario_with_endpoint(String scenario, String endpoint) {
	   
		payload_URL=Routes.base_URL+endpoint;
		if(scenario.equals("positive"))
		{
			payload_URL=Routes.base_URL+endpoint+UserCRUDFuntions.getUSER_FIRST_NAME();
			
		}
	}

	@When("User send GET request by username {string}")
	public void user_send_get_request_by_username(String type) {

		response=UserCRUDFuntions.get_byusername(payload_URL);


	}

	@Then("response should be validated for statuscode {int}, message {string}")
	public void response_should_be_validated_for_statuscode_message(Integer code, String message) {

		int statusCode = response.getStatusCode();
		String statusMessage = response.getStatusLine();
		System.out.println("stuscode n message:"+statusCode);
		System.out.println(statusMessage);
	}

	//--------------GET BY USERID---------------//

		@Given("User set GET request for scenario {string} with endpoint {string}")
		public void user_set_get_request_for_scenario_with_endpoint(String scenario, String endpoint) {

			payload_URL=Routes.base_URL+endpoint;
			
			if (scenario.equals("positive"))
			{
				
				Endpoint="user/"+UserCRUDFuntions.getUSER_ID();
				payload_URL=Routes.base_URL+Endpoint;

			}


		}

		@When("User send GET request by userID")
		public void user_send_get_request_by_user_id() {

			response=UserCRUDFuntions.getBy_userId(payload_URL);
			System.out.println(response.asPrettyString());

		}


	//--------------DELETE BY USERNAME------------//

	@Given("User set DELETE request for scenario {string} with endpoint {string}")
	public void user_set_delete_request_for_scenario_with_endpoint(String scenario, String endpoint) {

		Endpoint=endpoint;
		payload_URL=Routes.base_URL+Endpoint;

		if (scenario.equals("positive"))
		{
			Endpoint="deleteuser/username/"+UserCRUDFuntions.getUSER_FIRST_NAME();
			payload_URL=Routes.base_URL+Endpoint;

		}
	}

	@When("User send DELETE request by userName")
	public void user_send_delete_request_by_user_name() {

		 response=UserCRUDFuntions.delete_byuserName(payload_URL);

	}

	

	//---------------PUT SCENARIOS-------------//

	@Given("User set request body  for PUT from data file with scenario {string} and endpoint {string}")
	public void user_set_request_body_for_put_from_data_file_with_scenario_and_endpoint(String scenario, String endpoint) 
	
	{
		payload_URL=Routes.base_URL+endpoint+"/"+UserCRUDFuntions.getUSER_ID();
		
				// Read JSON data from the specified file
				UserDetailsJsonReader jsonReader=new UserDetailsJsonReader();
				UserDetails userobj = new UserDetails();

				try {
					userobj = jsonReader.user_json_Reader(Routes.filePath,scenario);
				} catch (IOException e) {
					e.printStackTrace();
				}

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				JsonRequestBody = gson.toJson(userobj);

		System.out.println(response.asPrettyString());
		userId = response.getBody().jsonPath().getString("user_id");	
		System.out.println(response.getStatusCode());
			}
	
@When("user sends API PUT request")
public void user_sends_api_put_request()
{
	
response=UserCRUDFuntions.update_user(JsonRequestBody, payload_URL);

if(response.getStatusCode()==200)
{
	userFirstName=response.getBody().jsonPath().getString("user_first_name");

	userId = response.getBody().jsonPath().getString("user_id");	
	
	int user_Id=(Integer.parseInt(userId));

	UserCRUDFuntions.setUSER_ID(user_Id);

	UserCRUDFuntions.setUSER_FIRST_NAME(userFirstName);
}
userId = response.getBody().jsonPath().getString("user_id");	
System.out.println(response.getStatusCode());


}



	//-----------PostByMandatoryFields

	@When("User sends post mandatory fields request with endpoint")
	public void user_sends_post_mandatory_fields_request_with_endpoint() {

		response=UserCRUDFuntions.create_user(JsonRequestBody, payload_URL);
		
		System.out.println(response.asPrettyString());
	System.out.println(response.getStatusCode());

	if(response.getStatusCode()==201)
	{
		userFirstName=response.getBody().jsonPath().getString("user_first_name");

		userId = response.getBody().jsonPath().getString("user_id");	
		int user_Id=(Integer.parseInt(userId));

		UserCRUDFuntions.setUSER_ID(user_Id);

		UserCRUDFuntions.setUSER_FIRST_NAME(userFirstName);
	}




	}
	
	@Then("response body for mandatory post and response code {int} is validated")
	public void response_body_for_mandatory_post_and_response_code_is_validated(Integer exp_code) {

		Assert.assertEquals(response.getStatusCode(),exp_code );
		
		if(response.getStatusCode()==201) 
		{
		System.out.println("userID from UserCRUDFunctions : "+UserCRUDFuntions.getUSER_ID());
		System.out.println("userName from UserCRUDFunctions : "+UserCRUDFuntions.getUSER_FIRST_NAME());
		
		Assert.assertEquals(response.getContentType(), "application/json");//content-type validation
		
		String firstName=response.jsonPath().getString("user_first_name");
		String lastName=response.jsonPath().getString("user_last_name");
		String phNo=response.jsonPath().getString("user_contact_number");
		String email=response.jsonPath().getString("user_email_id");
		assert firstName instanceof String:"user_first_name not a string";//datatype validation
		assert lastName instanceof String:"user_first_name not a string";
		assert phNo instanceof String:"user_first_name not a string";
		assert email instanceof String:"user_first_name not a string";
	}
	}

	//-----------DeleteByUserID
	
	@Given("User set DELETE BY USERID request for scenario {string} with endpoint {string}")
	public void user_set_delete_by_userid_request_for_scenario_with_endpoint(String scenario, String endpoint) {

		Endpoint=endpoint;
		payload_URL=Routes.base_URL+Endpoint;

		if (scenario.equals("positive"))
		{
			Endpoint="deleteuser/"+UserCRUDFuntions.getUSER_ID();
			payload_URL=Routes.base_URL+Endpoint;

		}
	}

	
	@When("User send DELETE request by userID")
	public void user_send_DELETE_request_by_user_id() {

		response=UserCRUDFuntions.delete_byuserId(payload_URL);

	}


}
