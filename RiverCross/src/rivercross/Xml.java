
package rivercross;

import java.io.File;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Xml {
    public void createXml() throws Exception
    {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder =  documentBuilderFactory.newDocumentBuilder();
        Document document =documentBuilder.newDocument();
        Element element=document.createElement("Developer");
        document.appendChild(element);
        
        Attr attr = document.createAttribute("ID");
        attr.setValue("1");
        
        element.setAttributeNode(attr);
        Element name =document.createElement("Name");
        name.appendChild(document.createTextNode("yaya"));
        element.appendChild(name);
        
        Element surName =document.createElement("surname");
        surName.appendChild(document.createTextNode("ihab"));
        element.appendChild(surName);
        
        Element age =document.createElement("age");
        age.appendChild(document.createTextNode("20"));
        element.appendChild(age);
        
        
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("C:\\XML\\data.xml"));
        transformer.transform(source, streamResult);
        
    }
    public void ReadXml() throws Exception
    {
        File XmlFile = new File("C:\\XML\\data.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder =  documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(XmlFile);
        
        NodeList list = document.getElementsByTagName("Developer");
        for(int i =0;i<list.getLength();i++)
        {
            Node node = list.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE)
            {
                Element element = (Element)node;
                System.out.println(element.getAttribute("ID"));
                System.out.println(element.getElementsByTagName("Name").item(0).getTextContent());
                System.out.println(element.getElementsByTagName("surname").item(0).getTextContent());
                System.out.println(element.getElementsByTagName("age").item(0).getTextContent());
            }
        }
    }
}
