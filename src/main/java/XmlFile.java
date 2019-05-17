import java.util.LinkedList;
import java.util.List;

public class XmlFile {
    private String name;
    private String text;
    private List<XmlFile> children;

    XmlFile(String name, String text){
        this.name = name;
        this.text = text;
        children = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public List<XmlFile> getChildren() {
        return children;
    }

    public String getText() {
        return text;
    }

    public XmlFile addChildren(String name, String text){
        XmlFile xmlFile = new XmlFile(name, text);
        children.add(xmlFile);
        return xmlFile;
    }
}
