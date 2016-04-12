package eetac.upc.eetac.dsa.grouptalk.dao;

/**
 * Created by pauli on 12/04/2016.
 */
public class GroupDAO {

    public Group crear_grupo(String nombre)throws SQLException,GroupAlreadyExistException;
    public boolean ingresar_grupo(String grupoid,String userid)throws SQLException,GroupNoExisteException,UserNoExisteException;
    public boolean abandonar_grupo(String grupoid,String userid)throws SQLException,GroupNoExisteException,UserNoExisteException,RelacionNoExisteException;
    public GroupCollection obtener_coleccion()throws SQLException;
    public Group obtener_NOMBRE_por_ID(String id)throws SQLException;
    public Group obtener_ID_grupo_por_NOMBRE(String nombre)throws SQLException;
    public Group comprobarUsuarioengrupo(String grupoid, String userid)throws RelacionNoExisteException, SQLException;

}
