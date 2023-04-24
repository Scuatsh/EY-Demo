package cl.barucvilla.EY;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber.json" }, features = { "src/test/resources" })
public class CucumberIntegrationTest {
}