package com;

import model.App;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType; 

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.*; 

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Appointments") 
public class AppService {

	App AppObj = new App(); 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readApp()
	 {
	 return AppObj.readApp();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertApp(@FormParam("app_id") int app_id,
			 @FormParam("pat_id") String pat_id,
			 @FormParam("DOC_id") String DOC_id,
			 @FormParam("time") String time,
			 @FormParam("date") String date)
	{
	 String output = AppObj.insertApp(app_id, pat_id, DOC_id, time, date);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateApp(String AppData)
	{
	//Convert the input string to a JSON object
	 JsonObject AppObject = new JsonParser().parse(AppData).getAsJsonObject();
	//Read the values from the JSON object
	 String app_id = AppObject.get("app_id").getAsString();
	 String pat_id = AppObject.get("pat_id").getAsString();
	 String DOC_id = AppObject.get("DOC_id").getAsString();
	 String time = AppObject.get("time").getAsString();
	 String date = AppObject.get("date").getAsString();
	 String output = AppObj.updateApp(app_id, pat_id, DOC_id, time, date);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteApp(String AppData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(AppData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String app_id = doc.select("app_id").text();
	 String output = AppObj.deleteApp(app_id);
	return output;
	}



}
