package eetac.upc.eetac.dsa.grouptalk.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import eetac.upc.eetac.dsa.grouptalk.entity.Group;
import eetac.upc.eetac.dsa.grouptalk.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pauli on 12/04/2016.
 */
public class GroupDAOImpl {
    @Override
    public Group crear_grupo(String nombre)throws SQLException,GroupAlreadyExistException {
        Connection connection = null;
        PreparedStatement stmt = null;
        String id = null;
        try {
            Group grupo = obtener_ID_grupo_por_NOMBRE(nombre);
            if (grupo != null)
                throw new GroupAlreadyExistException();
            connection = Database.getConnection();
            stmt = connection.prepareStatement(GroupDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();
            connection.setAutoCommit(false);
            stmt.close();
            stmt = connection.prepareStatement(GroupDAOQuery.CREAR_GRUPO);
            stmt.setString(1, id);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return obtener_NOMBRE_por_ID(id);
    }
    @Override
    public boolean ingresar_grupo(String nombre_grupo,String nombre_usuario)throws SQLException,GroupNoExisteException,UserNoExisteException{
        Connection connection = null;
        PreparedStatement stmt = null;
        UserDAOImpl comprobarUser = new UserDAOImpl();
        try {
            Group grupo = obtener_ID_grupo_por_NOMBRE(nombre_grupo);
            comprobarUser.obtener_UserByLoginid(nombre_usuario);
            if (grupo == null)throw new GroupNoExisteException();
            if (comprobarUser == null) throw new UserNoExisteException();
            connection = Database.getConnection();
            stmt = connection.prepareStatement(GroupDAOQuery.INGRESAR_GRUPO);
            stmt.setString(1, obtener_ID_grupo_por_NOMBRE(nombre_grupo).getId());
            stmt.setString(2,comprobarUser.obtener_UserByLoginid(nombre_usuario).getId());
            stmt.executeUpdate();
            return true ;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

    }
    @Override
    public boolean abandonar_grupo(String nombregrupo,String nombreusuario)throws SQLException,GroupNoExisteException, UserNoExisteException,RelacionNoExisteException{
        Connection connection = null;
        PreparedStatement stmt = null;
        UserDAOImpl comprobarUser = new UserDAOImpl();
        User user = null;
        try {
            Group grupo =obtener_ID_grupo_por_NOMBRE(nombregrupo);
            user = comprobarUser.obtener_UserByLoginid(nombreusuario);
            if (grupo == null)throw new GroupNoExisteException();
            if (user == null) throw new UserNoExisteException();
            grupo = comprobarUsuarioengrupo(grupo.getId(), user.getId());
            if (grupo == null)throw new RelacionNoExisteException();
            connection = Database.getConnection();
            stmt = connection.prepareStatement(GroupDAOQuery.ABANDONAR_GRUPO);
            stmt.setString(1,grupo.getId());
            stmt.setString(2,user.getId());
            stmt.executeUpdate();
            return true ;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

    }
    @Override
    public GroupCollection obtener_coleccion()throws SQLException{
        Group grup = null;
        GroupCollection grupocollection = new GroupCollection();
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement(GroupDAOQuery.OBTENER_GRUPOS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                grup = new Group();
                grup.setId(rs.getString("id"));
                grup.setNombre(rs.getString("nombre"));
                grupocollection.getGroups().add(grup);
            }
        }
        catch (SQLException e)
        {
            throw e;
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return grupocollection;
    }
    @Override
    public Group obtener_NOMBRE_por_ID(String id)throws SQLException{
        Group grupo = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement(GroupDAOQuery.OBTENER_NOMBRE_POR_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                grupo = new Group();
                grupo.setId(rs.getString("id"));
                grupo.setNombre(rs.getString("nombre"));
            }
        }
        catch (SQLException e)
        {
            throw e;
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return grupo;
    }
    @Override
    public Group obtener_ID_grupo_por_NOMBRE(String nombre)throws SQLException{
        Group grupo = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement(GroupDAOQuery.OBTENER_ID_GRUPO_POR_NOMBRE);
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                grupo = new Group();
                grupo.setId(rs.getString("id"));
                grupo.setNombre(rs.getString("nombre"));
            }
        }
        catch (SQLException e)
        {
            throw e;
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return grupo;
    }
    @Override
    public Group comprobarUsuarioengrupo(String grupoid, String userid) throws SQLException, RelacionNoExisteException {

        Connection connection = null;
        PreparedStatement stmt = null;
        Group grupo = null;
        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement(GroupDAOQuery.COMPROBAR_USER_ASIGNADO_A_GRUPO);
            stmt.setString(1, grupoid);
            stmt.setString(2, userid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                grupo = new Group();
                grupo.setId(rs.getString("grupoid"));
            }
        }
        catch (SQLException e)
        {
            throw e;
        }
        finally
        {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return grupo;
    }
}
