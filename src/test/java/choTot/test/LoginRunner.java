package choTot.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@Test
@CucumberOptions(features = "src/test/resources/features",
        glue = {"choTot.login.stepdefinitions", "hooks.TestHooks"},
        plugin = {"pretty", "html:target/cucumber-html-report.html"})
public class LoginRunner extends AbstractTestNGCucumberTests {
}
