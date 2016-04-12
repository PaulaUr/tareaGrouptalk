package eetac.upc.eetac.dsa.grouptalk.auth;

import javax.management.relation.Role;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pauli on 12/04/2016.
 */
public class UserInfo implements Principal{
    private String name;
    private List<Role> roles = new ArrayList<>();

    public UserInfo() {
    }

    public UserInfo(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
