package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features/flipkart_e2e.feature",
				glue = "stepDefinitions",
				plugin = {"pretty",
						"html:target/cucumber-reports/cucumber.html",
						"json:target/cucumber-reports/cucumber.json",
						"junit:target/cucumber-reports/cucumber.xml"}
				)


public class TestRunner extends AbstractTestNGCucumberTests  {

}
