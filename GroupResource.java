package eetac.upc.eetac.dsa.grouptalk;

import eetac.upc.eetac.dsa.grouptalk.dao.GroupDAO;
import eetac.upc.eetac.dsa.grouptalk.dao.GroupDAOImpl;
import eetac.upc.eetac.dsa.grouptalk.entity.Group;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;


/**
 * Created by pauli on 12/04/2016.
 */
public class GroupResource {

    @Path("grupo")
    public class GroupResource {
        @Context
        private SecurityContext securityContext;
        @RolesAllowed({"administrador"})
        @POST
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(GrouptalkMediaType.GROUPTALK_GRUPO)
        public Response crearGrupo(@FormParam("nombre") String nombre, @Context UriInfo uriInfo) throws URISyntaxException, GroupAlreadyExistException, SQLException
        {
            if(nombre == null)
                throw new BadRequestException("es necesario un nombre de grupo");
            GroupDAO grupoDAO = new GroupDAOImpl();
            Group grupo = null;

            try{
                grupo = grupoDAO.crear_grupo(nombre);

            }catch (GroupAlreadyExistException e){
                throw new WebApplicationException("este grupo ya existe!!", Response.Status.CONFLICT);
            }catch(SQLException e){
                throw new InternalServerErrorException();
            }
            URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + grupo.getId());
            return Response.created(uri).type(GrouptalkMediaType.GROUPTALK_GRUPO).entity(grupo).build();

        }
        @RolesAllowed({"registrado"})
        @Path(("/ingresar_grupo"))
        @POST
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        public Response ingresarGrupo(@FormParam("nombregrupo") String nombregrupo,@FormParam("nombreuser") String nombreuser, @Context UriInfo uriInfo) throws URISyntaxException, GrupoNoExisteException, UserNoExisteException, SQLException
        {

            if(nombregrupo == null||nombreuser == null)
                throw new BadRequestException("es necesario rellenar todos los campos");
            GroupDAO grupoDAO = new GroupDAOImpl();
            try
            {
                grupoDAO.ingresar_grupo(nombregrupo,nombreuser);

            }
            catch (GrupoNoExisteException e)
            {
                throw new WebApplicationException("este grupo no existe!!", Response.Status.CONFLICT);
            }
            catch (UserNoExisteException e) {
                throw new WebApplicationException("este grupo no existe!!", Response.Status.CONFLICT);
            }
            catch(SQLException e)
            {
                throw new InternalServerErrorException();
            }
            return Response.ok().build();

        }
        @RolesAllowed({"registrado"})
        @Path(("/abandonar_grupo"))
        @POST
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        public Response abandonarGrupo(@FormParam("nombregrupo") String nombregrupo,@FormParam("nombreuser") String nombreuser, @Context UriInfo uriInfo) throws URISyntaxException, GrupoNoExisteException, UserNoExisteException, SQLException,RelacionNoExisteException
        {
            if(nombregrupo == null||nombreuser == null)
                throw new BadRequestException("es necesario rellenar todos los campos");
            GroupDAO grupoDAO = new GroupDAOImpl();
            try
            {
                grupoDAO.abandonar_grupo(nombregrupo, nombreuser);

            }
            catch (GrupoNoExisteException e)
            {
                throw new WebApplicationException("este grupo no existe!!", Response.Status.CONFLICT);
            }
            catch (UserNoExisteException e) {
                throw new WebApplicationException("este usuario no existe!!", Response.Status.CONFLICT);
            }
            catch(SQLException e)
            {
                throw new InternalServerErrorException();
            }
            return Response.ok().build();

        }

        @Path(("/obtenergrupos"))
        @GET
        @Produces(GrouptalkMediaType.GROUPTALK_GRUPO_COLLECTION)
        public GroupCollection obtenerGrupo() throws URISyntaxException, SQLException
        {
            GroupDAO grupoDAO = new GroupDAOImpl();
            GroupCollection colecciongrupos = new GroupCollection();
            try
            {
                colecciongrupos = grupoDAO.obtener_coleccion();

            }
            catch(SQLException e)
            {
                throw new InternalServerErrorException();
            }
            return colecciongrupos;
        }
    }

}
