package eetac.upc.eetac.dsa.grouptalk.entity;

/**
 * Created by pauli on 12/04/2016.
 */
public class GrouptalkRootAPI {
    @InjectLinks({
            @InjectLink(resource = GrouptalkRootAPIResource.class, style = InjectLink.Style.ABSOLUTE, rel = "self bookmark home", title = "Grouptalk Root API"),
            @InjectLink(resource = LoginResource.class, style = InjectLink.Style.ABSOLUTE, rel = "login", title = "Login",  type= GrouptalkMediaType.GROUPTALK_AUTH_TOKEN),
            @InjectLink(resource = GroupResource.class, style = InjectLink.Style.ABSOLUTE, rel = "current-stings", title = "Current stings", type= GrouptalkMediaType.GROUPTALK_GRUPO_COLLECTION),
            @InjectLink(resource = UserResource.class, style = InjectLink.Style.ABSOLUTE, rel = "create-user", title = "Register", type= GrouptalkMediaType.GROUPTALK_AUTH_TOKEN),
            @InjectLink(resource = LoginResource.class, style = InjectLink.Style.ABSOLUTE, rel = "logout", title = "Logout", condition="${!empty resource.userid}"),
            @InjectLink(resource = UserResource.class, method="getUser", style = InjectLink.Style.ABSOLUTE, rel = "user-profile", title = "User profile", condition="${!empty resource.userid}", type= GrouptalkMediaType.GROUPTALK_USER, bindings = @Binding(name = "id", value = "${resource.userid}"))
    })
    private List<Link> links;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

}
