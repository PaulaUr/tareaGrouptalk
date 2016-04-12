package eetac.upc.eetac.dsa.grouptalk.dao;

import eetac.upc.eetac.dsa.grouptalk.entity.User;

import java.sql.SQLException;

/**
 * Created by pauli on 12/04/2016.
 */
public interface UserDAO {
    public User crear_usuario(String loginid, String password, String email, String fullname) throws SQLException, UserAlreadyExistsException;
    public User obtener_UserById(String id) throws SQLException;
    public User obtener_UserByLoginid(String loginid) throws SQLException;
    public boolean check_Password(String id, String password) throws SQLException;
}
