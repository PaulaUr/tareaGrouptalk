package eetac.upc.eetac.dsa.grouptalk.dao;

import eetac.upc.eetac.dsa.grouptalk.auth.Authorized;
import eetac.upc.eetac.dsa.grouptalk.auth.UserInfo;
import eetac.upc.eetac.dsa.grouptalk.entity.AuthToken;

import java.sql.SQLException;

/**
 * Created by pauli on 12/04/2016.
 */
public class AuthTokenDAO {
    public UserInfo getUserByAuthToken(String token) throws SQLException {
        return null;
    }

    public AuthToken createAuthToken(String userid) throws SQLException {
        return null;
    }

    public void deleteToken(String userid) throws SQLException {

    }
}
