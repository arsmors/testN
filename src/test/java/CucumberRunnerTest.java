import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin ="pretty",
        glue = "steps")

public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}
