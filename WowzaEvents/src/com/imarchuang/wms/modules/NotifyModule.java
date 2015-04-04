package com.imarchuang.wms.modules;

import com.wowza.wms.application.*;
import com.wowza.wms.amf.*;
import com.wowza.wms.client.*;
import com.wowza.wms.module.*;
import com.wowza.wms.request.*;
import com.wowza.wms.stream.*;
import com.wowza.wms.rtp.model.*;
import com.wowza.wms.httpstreamer.model.*;
import com.wowza.wms.httpstreamer.cupertinostreaming.httpstreamer.*;
import com.wowza.wms.httpstreamer.smoothstreaming.httpstreamer.*;

//import for restful web service using jersey
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class NotifyModule extends ModuleBase {

	public void doSomething(IClient client, RequestFunction function,
			AMFDataList params) {
		getLogger().info("doSomething");
		sendResult(client, params, "Hello Wowza: I am tesing your module extension capabilities");
	}
	//@Override
	public void onAppStart(IApplicationInstance appInstance) {
		String fullname = appInstance.getApplication().getName() + "/"
				+ appInstance.getName();
		getLogger().info("onAppStart: " + fullname);
		getLogger().info("Push the onAppStart event of " + appInstance.getApplication().getName() + "to notification server");
		ServiceClient.callService("notify", appInstance.getApplication().getName());
	}

	public void onAppStop(IApplicationInstance appInstance) {
		String fullname = appInstance.getApplication().getName() + "/"
				+ appInstance.getName();
		getLogger().info("onAppStop: " + fullname);
	}

	public void onConnect(IClient client, RequestFunction function,
			AMFDataList params) {
		getLogger().info("onConnect: " + client.getClientId());
	}

	public void onConnectAccept(IClient client) {
		getLogger().info("onConnectAccept: " + client.getClientId());
	}

	public void onConnectReject(IClient client) {
		getLogger().info("onConnectReject: " + client.getClientId());
	}

	public void onDisconnect(IClient client) {
		getLogger().info("onDisconnect: " + client.getClientId());
	}

	public void onStreamCreate(IMediaStream stream) {
		getLogger().info("onStreamCreate: " + "streamName:"+stream.getName() + "client:"+stream.getClient() 
				+ "clientID:"+stream.getClientId() + "src:"+stream.getSrc());
	}
	
	/**
	 * 2015-03-26      21:51:55        UTC     comment server  ERROR   500     -       
	 * invoke(onStreamCreate): java.lang.IllegalArgumentException: wrong number of arguments|
	 * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)|at sun.reflect.NativeMethodAccessorImpl.
	 * invoke(NativeMethodAccessorImpl.java:57)|at sun.reflect.DelegatingMethodAccessorImpl.invoke
	 * (DelegatingMethodAccessorImpl.java:43)|at java.lang.reflect.Method.invoke(Method.java:606)|at 
	 * com.wowza.wms.module.ModuleFunction.invoke(ModuleFunction.java:383)
	 * **/
	/*
	public void onStreamCreate(IApplicationInstance appInstance, IMediaStream stream) {
		String fullname = appInstance.getApplication().getName() + "/"
				+ appInstance.getName();
		//getLogger().info("onAppStart: " + fullname);
		getLogger().info("onStreamCreate: " + "appInstance:"+fullname+"streamName:"+stream.getName() + "client:"+stream.getClient() 
				+ "clientID:"+stream.getClientId() + "src:"+stream.getSrc());
	}
	*/

	public void onStreamDestroy(IMediaStream stream) {
		getLogger().info("onStreamDestroy: " + "streamName:"+stream.getName() + "client:"+stream.getClient() 
				+ "clientID:"+stream.getClientId() + "src:"+stream.getSrc());
	}

}