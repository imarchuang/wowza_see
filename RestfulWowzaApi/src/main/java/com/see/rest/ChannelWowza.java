package com.see.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.see.rest.util.Command;

@Path("/create")
public class ChannelWowza {
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		
		Command cmd = new Command();
		
		String command = "/usr/local/WowzaStreamingEngine/examples/createChannel.sh " + msg;
		 
		String output = cmd.executeCommand(command);
 
		return Response.status(200).entity(output).build();
 
	}
}
