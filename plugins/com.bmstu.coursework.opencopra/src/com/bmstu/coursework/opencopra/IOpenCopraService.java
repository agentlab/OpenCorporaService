/**
 *
 */
package com.bmstu.coursework.opencopra;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 *
 * @author Naymushin
 *
 */
@Path("opencorpora")
public interface IOpenCopraService {

	@Path("/{servicename}/{action}")
	@POST
	Response post(@PathParam("servicename") String service, @PathParam("action") String action, @QueryParam("section") String section, @QueryParam("name") String name, @QueryParam("left") String left, @QueryParam("right") String right, @QueryParam("id") String id, @QueryParam("params") final List<String> params);

	@Path("/{servicename}/{action}")
	@GET
	Response get(@PathParam("servicename") String service, @PathParam("action") String action, @QueryParam("section") String section, @QueryParam("name") String name, @QueryParam("left") String left, @QueryParam("right") String right, @QueryParam("id") String id);

	@Path("/{servicename}/{action}")
	@PUT
	Response put(@PathParam("servicename") String service, @PathParam("action") String action, @QueryParam("section") String section, @QueryParam("name") String name, @QueryParam("left") String left, @QueryParam("right") String right, @QueryParam("id") String id, @QueryParam("params") final List<String> params);

	@Path("/{servicename}/{action}")
	@DELETE
	Response delete(@PathParam("servicename") String service, @PathParam("action") String action, @QueryParam("section") String section, @QueryParam("name") String name, @QueryParam("left") String left, @QueryParam("right") String right, @QueryParam("id") String id);
}
