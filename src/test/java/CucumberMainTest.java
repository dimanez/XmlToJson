import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        dryRun = false,
        strict = true,
        snippets = SnippetType.UNDERSCORE)

public class CucumberMainTest {


}