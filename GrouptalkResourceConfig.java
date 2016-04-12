package eetac.upc.eetac.dsa.grouptalk;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by pauli on 12/04/2016.
 */
public class GrouptalkResourceConfig extends ResourceConfig{

    public GrouptalkResourceConfig() {
        packages("edu.upc.eetac.dsa.grouptalk");
        packages("edu.upc.eetac.dsa.grouptalk.auth");
        register(RolesAllowedDynamicFeature.class);
    }
}
