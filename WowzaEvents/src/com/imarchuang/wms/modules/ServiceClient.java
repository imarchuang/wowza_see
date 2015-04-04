package com.imarchuang.wms.modules;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class ServiceClient {
	final static Logger logger = Logger.getLogger(ServiceClient.class);
	static final String path = "/usr/local/WowzaStreamingEngine/lib/conf/log4j.properties";
	
	//test in Windows
	//static final String path = "C:/workspace/conf/log4j.properties";

	public static void callService(String serviceName, String channelName) {
		try {

			PropertyConfigurator.configure(path);
			ClientConfig config = new ClientConfig();		
			Client client = ClientBuilder.newClient(config);
			logger.debug("Received serviceName: "+serviceName + " channelName "+ channelName);
			WebTarget webTarget = client.target(getBaseURI(serviceName));
		    //MultivaluedMap<String, String> formData = new MultivaluedHashMap<String, String>();
		    //formData.add("channelName", "imarc");
		    String payload = "{\"channelName\": \""+ channelName +"\"}";
		    logger.debug("payload: "+payload);
		    //formData.add("key2", "value2");
		    Response response = webTarget.request().accept(MediaType.APPLICATION_JSON).post(Entity.entity(payload, MediaType.APPLICATION_JSON), Response.class);

//		    System.out.println(response.getEntityTag() + "\n");
//		    System.out.println(response.getStatus() + "\n");
//		    System.out.println(response + "\n");

	        // check response status code
	        if (response.getStatus() != 200) {
	        	logger.error("Failed : HTTP error code : " + response.getStatus());
	            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
	        }

	        // display response
	        Object output = response.readEntity(String.class);
	        logger.info("Output from Server: "+output);
	        
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse((String) output);
	        
			boolean success = (Boolean) jsonObject.get("success");
			logger.debug("success: "+success);
	 
			String errorMessage = (String) jsonObject.get("errorMessage");			
			logger.debug("errorMessage: "+errorMessage);
	    } catch (Exception e) {
	        e.printStackTrace();
	        logger.error(e);
	    }

	}

	private static URI getBaseURI(String propName) {
		Properties prop = new Properties();
		InputStream input = null;
		String serviceURL = null;
	 
		try {
			input = new FileInputStream("/usr/local/WowzaStreamingEngine/lib/conf/services.properties");
			
			//test in Windows
			//input = new FileInputStream("C:/workspace/conf/services.properties");
	 
			// load a properties file
			prop.load(input);
			serviceURL = prop.getProperty(propName);
			logger.debug("serviceURL for "+propName +" is: "+serviceURL);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(serviceURL != null){
			return UriBuilder.fromUri(serviceURL).build();
		}else{
			logger.error("null return for property "+propName);
			return null;
		}
	}

}
