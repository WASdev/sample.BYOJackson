/**
 * (C) Copyright IBM Corporation 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample.byojackson;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


/**
 * This class is the sample resource.  It is a store of Widget objects.  It
 * creates one Widget instance in the constructor (id=0), and allows users to
 * add their own.  All instances are stored in memory and are not persistent
 * (this is just a sample after all! :-) ).  
 *
 * Users can view existing Widget instances via their public ID (note that we
 * convert the public ID to an internal ID in order to show off the JsonIgnore
 * annotation in the Widget class).
 *
 * There is also a method that does the same call but using the Client APIs to
 * invoke this same resource (currently it assumes the server is using port
 * 9080).  Note that the client API code in the getWidgetViaClientAPI method
 * must explicitly register the JacksonJsonProvider.
 */ 
@Path("/widget")
public class WidgetResource {

	static Map<Integer,Widget> _widgets = new HashMap<>();
	
	public WidgetResource() {
		// create an initial widget so we have something to find easily
		Widget w = new Widget();
		w.internalID = 1000;
		w.publicID = 0;
		w.name = "Invisibility Cloak";
		w.qty = 7;
		_widgets.put(w.internalID, w);
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Widget getWidget(@PathParam("id") int id) {
		return _widgets.get(1000 + id);
	}
	
	@GET // this should be @POST, but @GET is easier to test in a browser..
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Widget createWidget(@QueryParam("name") String name, 
			           @QueryParam("qty") int qty) {
		int id = Widget.nextID.incrementAndGet();
		Widget w = new Widget();
		w.internalID = 1000 + id;
		w.publicID = id;
		w.name = name;
		w.qty = qty;
		_widgets.put(w.internalID, w);
		return w;
	}
	
	@GET
	@Path("/viaClient/{id}") // this tests the client API
	@Produces(MediaType.APPLICATION_JSON)
	public Widget getWidgetViaClientAPI(@PathParam("id") int id) {
		Client client = ClientBuilder.newClient();
		client.register(JacksonJsonProvider.class);
		WebTarget target = client.target(
			"http://localhost:9080/servlet/byoj/widget/")
			.path("/{id}").resolveTemplate("id", id);
		Widget w = target.request().get(Widget.class);
		System.out.println("Obtained " + w);
		return w;
		
	}
}
