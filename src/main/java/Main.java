import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    private XmlFile file;

    /**
     * Парсинг файла формата Xml в JSON.
     * @param inputFileName имя файла на вход.
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void writeXmlToJson(String inputFileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try {
            Document document = builder.parse(new File("src/main/resources/" + inputFileName + ".xml"));
            Element element = document.getDocumentElement();

            file = new XmlFile(element.getNodeName(), element.getNodeValue());
            visitChildNodes(element.getChildNodes(), file);
            createJsonFile(file, inputFileName);
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
    }

    /**
     * Создаем JSON файл на основе экземпляра класа XmlFile с деревом элементов.
     * @param file экземпляр класса
     * @param fileName имя нового JSON файла
     */
    private void createJsonFile(XmlFile file, String fileName){
        //Object to JSON file
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writer().withDefaultPrettyPrinter().writeValue(new File(fileName +".json"), file);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Проходим по всем дочерним элементам, получая getNodeName и getNodeValue
     * @param nList NodeList с корневого элемента
     * @param xmlFile экземпляр класса для добавления в него дочерних узлов.
     */
    private void visitChildNodes(NodeList nList, XmlFile xmlFile){
        for (int i = 0; i < nList.getLength(); i++){
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE){


                String val = "";
                if (node.hasChildNodes()) {
                    NodeList sublist = node.getChildNodes();
                    for (int j = 0; j < sublist.getLength(); j++) {
                        if (sublist.item(j).getNodeType() == Node.TEXT_NODE)
                            val += sublist.item(j).getNodeValue();
                    }
                }
                else val = null;

                //Мб. и не нужно.
                val = val.replaceAll("  ", "").replaceAll("\n", "").replaceAll("\t", "");
                if (val.equals(" ") || val.equals("\n") || val.equals("")){
                    val = null;
                }

                XmlFile file = xmlFile.addChildren(node.getNodeName(),val);
                if (node.hasChildNodes()) {
                    //Если имеются еще наследники, то проходим по циклу снова.
                    visitChildNodes(node.getChildNodes(), file);
                }
            }
        }
    }

    /**
     * Getter
     * @return XmlFile
     */
    public XmlFile getFile() {
        return file;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        new Main().writeXmlToJson("xml_file");
    }
}
