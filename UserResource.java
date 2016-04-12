package eetac.upc.eetac.dsa.grouptalk;

import eetac.upc.eetac.dsa.grouptalk.dao.AuthTokenDAOImpl;
import eetac.upc.eetac.dsa.grouptalk.dao.UserDAO;
import eetac.upc.eetac.dsa.grouptalk.dao.UserDAOImpl;
import eetac.upc.eetac.dsa.grouptalk.entity.AuthToken;
import eetac.upc.eetac.dsa.grouptalk.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by pauli on 12/04/2016.
 */
@Path("users")
public class UserResource {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(GrouptalkMediaType.GROUPTALK_AUTH_TOKEN)
    public Response registerUser(@FormParam("loginid") String loginid, @FormParam("password") String password, @FormParam("email") String email, @FormParam("fullname") String fullname, @Context UriInfo uriInfo) throws URISyntaxException {
        if(loginid == null || password == null || email == null || fullname == null)
            throw new BadRequestException("all parameters are mandatory");
        UserDAO userDAO = new UserDAOImpl();
        User user = null;
        AuthToken authenticationToken = null;
        try{
            user = userDAO.crear_usuario(loginid, password, email, fullname);
            authenticationToken = (new AuthTokenDAOImpl()).createAuthToken(user.getId());
        }catch (UserAlreadyExistsException e){
            throw new WebApplicationException("loginid already exists", Response.Status.CONFLICT);
        }catch(SQLException e){
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + user.getId());
        return Response.created(uri).type(GrouptalkMediaType.GROUPTALK_AUTH_TOKEN).entity(authenticationToken).build();
    }

}
