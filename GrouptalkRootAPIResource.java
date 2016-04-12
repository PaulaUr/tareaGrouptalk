package eetac.upc.eetac.dsa.grouptalk;

import eetac.upc.eetac.dsa.grouptalk.entity.GrouptalkRootAPI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by pauli on 12/04/2016.
 */
@Path("api")
public class GrouptalkRootAPIResource {

    @Context
    private SecurityContext securityContext;

    private String userid;

    @GET
    @Produces(GrouptalkMediaType.GROUPTALK_ROOT)
    public GrouptalkRootAPI getRootAPI() {
        if (securityContext.getUserPrincipal() != null)
            userid = securityContext.getUserPrincipal().getName();
        GrouptalkRootAPI grouptalkRootAPI = new GrouptalkRootAPI();

        return grouptalkRootAPI;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
