package interview._01_test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Сергей on 26.02.16.
 */
public class Trojan {
    public static int countCompromised(String xml, String infectedFileId) throws XMLStreamException {

        Set<String> infected = new HashSet<>();
        Set<String> temp = new HashSet<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(xml));

        while(reader.hasNext()){
            int event = reader.next();

            switch(event){
                case XMLStreamConstants.START_ELEMENT:
                    if ("file".equals(reader.getLocalName())) {
                        temp.add(reader.getAttributeValue(0));
                    } else {
                        if (temp.size() > 0 && temp.contains(infectedFileId)) {
                            temp.remove(infectedFileId);
                            infected.addAll(temp);
                        }
                        temp.clear();
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if (!"file".equals(reader.getLocalName()) &&
                            temp.size() > 0 &&
                            temp.contains(infectedFileId)) {
                        temp.remove(infectedFileId);
                        infected.addAll(temp);
                        temp.clear();
                    }
                    break;
            }
        }

        return infected.size();
    }

    public static void main(String[] args) throws XMLStreamException {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<root>" +
                        "   <snapshot>" +
                        "      <folder>" +
                        "         <file fileId=\"1\"/>" +
                        "         <file fileId=\"2\"/>" +
                        "         <folder>" +
                        "            <file fileId=\"3\"/>" +
                        "            <file fileId=\"4\"/>" +
                        "         </folder>" +
                        "      </folder>" +
                        "   </snapshot>" +
                        "   <snapshot>" +
                        "      <file fileId=\"1\"/>" +
                        "      <file fileId=\"3\"/>" +
                        "      <folder>" +
                        "         <file fileId=\"2\"/>" +
                        "         <file fileId=\"4\"/>" +
                        "         <folder>" +
                        "            <file fileId=\"3\"/>" +
                        "            <file fileId=\"4\"/>" +
                        "         </folder>" +
                        "      </folder>" +
                        "   </snapshot>" +
                        "</root>";

        System.out.println(Trojan.countCompromised(xml, "3"));
    }
}
