package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/flipkart_e2e.feature",
				glue = "stepDefinitions",
				plugin = {"pretty"}
				)


public class TestRunner extends AbstractTestNGCucumberTests  {

}
