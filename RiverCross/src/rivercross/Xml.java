
package rivercross;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class Xml {
    private String filePath;
    public Xml(String filePath){
        this.filePath=filePath;
    }
    public void createXml(Momento m) throws Exception
    {
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Momento.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(m, file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
    public Momento ReadXml(){
        Momento m=new Momento();
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Momento.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            m = (Momento) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return m;
    }


}
