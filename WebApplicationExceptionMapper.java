package eetac.upc.eetac.dsa.grouptalk;

import eetac.upc.eetac.dsa.grouptalk.entity.GrouptalkError;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by pauli on 12/04/2016.
 */
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException>{

    @Override
    public Response toResponse(WebApplicationException e) {
        GrouptalkError error = new GrouptalkError(e.getResponse().getStatus(), e.getMessage());
        return Response.status(error.getStatus()).entity(error).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
