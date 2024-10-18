package apiDemo.StepDefinitions;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import apiDemo.payload.AddressDetails;
import apiDemo.payload.UserDetails;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;

public class UserDetailsJsonReader {

	
        
    	public  UserDetails user_json_Reader(String filepath,String scenarioNode) throws IOException
    	{
    	ObjectMapper objectMapper = new ObjectMapper();
       
            // Read the JSON file
            JsonNode rootNode = objectMapper.readTree(new File(filepath));

            // Access the "scenarioNode" data
            JsonNode Scenario_Node = rootNode.path(scenarioNode);
            UserDetails invalidUnameData = objectMapper.treeToValue(Scenario_Node, UserDetails.class);
            JsonNode addressnode=Scenario_Node.path("userAddress");
            AddressDetails address=objectMapper.treeToValue(addressnode, AddressDetails.class);
              
            
            UserDetails userDetailsObj=new UserDetails();
			AddressDetails addressDetailsObj=new AddressDetails();
			
			
			addressDetailsObj.setPlotNumber(address.getPlotNumber());
			addressDetailsObj.setStreet(address.getStreet());
			addressDetailsObj.setState(address.getState());
			addressDetailsObj.setCountry(address.getCountry());
			addressDetailsObj.setZipCode(address.getZipCode());
			
			userDetailsObj.setUser_first_name(invalidUnameData.getUser_first_name());
			userDetailsObj.setUser_last_name(invalidUnameData.getUser_last_name());
			userDetailsObj.setUser_contact_number(invalidUnameData.getUser_contact_number());
			userDetailsObj.setUser_email_id(invalidUnameData.getUser_email_id());
			userDetailsObj.setUserAddress(addressDetailsObj);
			
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			 String JsonRequestBody = gson.toJson(userDetailsObj);
				System.out.println(JsonRequestBody);
				return userDetailsObj;
				
    	}
			
			    }




