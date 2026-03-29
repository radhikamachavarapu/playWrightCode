package cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
 @Suite
 @IncludeEngines("cucumber")
 @SelectClasspathResource("/features")
 @ConfigurationParameter(
        key="cucumber.plugin",
 //       value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm," +
           value= "pretty, " +
                "html:target/cucumber-reports/cucumber.html"
 )

 public class CucumberTestSuite {
 }
