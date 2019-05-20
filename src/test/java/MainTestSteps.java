import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MainTestSteps {
    private Main main = new Main();
    private ObjectMapper mapper = new ObjectMapper();

    @Пусть("на вход конвертора подан корректный XML файл {word}")
    public void inputFile(String fileName) throws IOException, SAXException, ParserConfigurationException {
        try {
            main.writeXmlToJson(fileName);
            Assert.assertTrue(true);
        }catch (SAXParseException e){
            Assert.assertTrue("Не корректный файл!", false);
        }

    }

    @Тогда("результат является корректным JSON")
    public void getJsonFile() throws JsonProcessingException {
        Assert.assertEquals("Корректный результат", mapper.writeValueAsString(main.getFile()), "{\"name\":\"root\",\"text\":null,\"children\":[{\"name\":\"element1\",\"text\":null,\"children\":[{\"name\":\"element2\",\"text\":\"text1\",\"children\":[]},{\"name\":\"element3\",\"text\":\"text2\",\"children\":[]},{\"name\":\"element5\",\"text\":\"texZZZt\",\"children\":[]}]},{\"name\":\"element4\",\"text\":\"text\",\"children\":[]}]}");
    }

    @Пусть("на вход поступает файл {word} с пятью элементами")
    public void getFiveElements(String fileName) throws IOException, SAXException, ParserConfigurationException {
        main.writeXmlToJson(fileName);
        Assert.assertEquals(main.getElementCount(), 5);
    }

    @Тогда("на вход поступает файл {word} с одним элементом")
    public void getOneElement(String fileName) throws IOException, SAXException, ParserConfigurationException {
        main.writeXmlToJson(fileName);
        Assert.assertEquals(main.getElementCount(), 1);
    }
}
