import java.io.File;
import java.io.IOException;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModifyApplicationXML {

	public static void main(String[] args) {
		String filePath = "/usr/local/WowzaStreamingEngine/examples/"+args[0]+"/conf/"+args[0]+"/Application.xml";
		
		//testing in Windows
		//String filePath = "C:\\Program Files (x86)\\Wowza Media Systems\\Wowza Streaming Engine 4.1.1\\examples\\"+args[0]+"\\conf\\"+args[0]+"\\Application.xml";
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try{
        	dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(xmlFile);
        	doc.getDocumentElement().normalize();
        	
        	//update Element value
        	updateElementValue(doc, args[0]);
        	
        	//write the updated document to file or console
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            //System.out.println("XML file updated successfully");
        	
        }
	//catch(SAXException | ParserConfigurationException | IOException | TransformerException e1){
        catch(Exception e1){
		e1.printStackTrace();
        }
	}

	private static void updateElementValue(Document doc, String n) {
		NodeList applications = doc.getElementsByTagName("Application");
        Element app = null;
        //loop for each employee
        for(int i=0; i<applications.getLength();i++){
        	app = (Element) applications.item(i);
            Node name = app.getElementsByTagName("Name").item(0).getFirstChild();
            name.setNodeValue(n);
        }
		
	}

}

