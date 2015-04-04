package com.see.rest.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//import org.apache.log4j.Logger;


public class Command {
	
	//private static Logger logger = Logger.getLogger(Command.class);
	
	public String executeCommand(String command) {
		StringBuffer output = new StringBuffer();
		 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
			//logger.debug(e);
		}
 
		return output.toString();
	}
	
	public String getOSInfo() {
	    String s = 
	      "name: " + System.getProperty ("os.name");
	    s += ", version: " + System.getProperty ("os.version");
	    s += ", arch: " + System.getProperty ("os.arch");
	    //System.out.println ("OS=" + s);
	    
	    return s;
	}
	
	//testing only
	public static void main(String[] args) {
		
		Command cmd = new Command();
		 
		String domainName = "aws_see";
 
		String command = null;
		//System.out.println(System.getProperty ("os.name").substring(0,7));
		//in linux 
		if (System.getProperty ("os.name") == "Linux"){
			command = "ping -c 3 " + domainName;
		}else if(System.getProperty ("os.name").substring(0,7).equals("Windows")){
			//in windows
			command = "ping -n 3 " + domainName;
		}else{
			command = "";//error
		}
		
		String output = cmd.executeCommand(command);
 
		System.out.println(output);
		

	}

}
