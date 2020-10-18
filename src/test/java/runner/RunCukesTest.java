package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {
        "pretty",
        "json:target/Cucumber-report.json",
        "junit:target/Cucumber-report.xml",
        "html:target/cucumber-html-report"
},
        monochrome = true,
        features = {"src/test/resources"},
        glue = {"stepDefs"}
)
public class RunCukesTest {
}
