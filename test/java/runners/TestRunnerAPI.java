package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

	@CucumberOptions (
		       	   
				//features={"src/test/java/FeatureFiles/GetFeature.feature"},

				  features={"C:\\Users\\kpsge\\DS_ALGO\\APIDemo\\src\\test\\java\\Features\\post_scenarios.feature"},
						 // features={"C:\\Users\\kpsge\\DS_ALGO\\APIDemo\\src\\test\\java\\Features\\PutScenarios.feature"},
				  //features={"C:\Users\kpsge\DS_ALGO\APIDemo\src\test\java\Features\login.feature"}
			 	  //features={".//FeatureFiles/Home.feature"},
			     				  
				  glue = "apiDemo.StepDefinitions",
				  plugin = {"pretty", "html:reports/myreport.html", //----Cucumber Report
						  "rerun:target/rerun.txt",

						
						  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",//---Extent Report 
	             "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",//--------cucumber Report

						  "timeline:target/timeline" //-----Timeline report
						   },

				  dryRun = false,
				  monochrome = true,
				  publish = true
				  //tags="@sanity"  // this will execute scenarios tagged with @sanity
				  //tags="@Trial"
				  //tags="@sanity and @regression" //Scenarios tagged with both @sanity and @regression
				  //tags="@sanity and not @regression" //Scenarios tagged with @sanity but not tagged with @regression
				  //tags="@sanity or @regression" //Scenarios tagged with either @sanity or @regression	
				  
			)

	public class TestRunnerAPI extends AbstractTestNGCucumberTests {
		
	  
	  
	}



