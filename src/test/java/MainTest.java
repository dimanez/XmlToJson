import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class MainTest {

    /*
    XML из одного элемента
     */
    @Test
    public void writeXmlToJsonTest1() throws IOException, SAXException, ParserConfigurationException {
        ObjectMapper mapper = new ObjectMapper();
        Main main = new Main();

        main.writeXmlToJson("one_element");

        String test = mapper.writeValueAsString(main.getFile());
        assertEquals("Дерево элементов JSON файла соответствует заданному XML-файлу", test, "{\"name\":\"root\",\"text\":null,\"children\":[]}");
    }

    /*
    XML с вложенностью из 5 элементов
     */
    @Test
    public void writeXmlToJsonTest2() throws IOException, SAXException, ParserConfigurationException {
        ObjectMapper mapper = new ObjectMapper();
        Main main = new Main();
        main.writeXmlToJson("five_elements");

        assertEquals("Дерево элементов JSON файла соответствует заданному XML-файлу", mapper.writeValueAsString(main.getFile()), "{\"name\":\"e1\",\"text\":null,\"children\":[{\"name\":\"e2\",\"text\":null,\"children\":[{\"name\":\"e3\",\"text\":null,\"children\":[{\"name\":\"e4\",\"text\":null,\"children\":[{\"name\":\"e5\",\"text\":\"text\",\"children\":[]}]}]}]}]}");
    }

    /*
    Некорректный XML
     */
    @Test
    public void writeXmlToJsonTest3() throws IOException, SAXException, ParserConfigurationException {
        ObjectMapper mapper = new ObjectMapper();
        Main main = new Main();

        try {
            main.writeXmlToJson("incorrenct");
            assertTrue(false);
        }catch (SAXParseException e){
            assertTrue("Некорректный XML файл", true);
        }
    }
}