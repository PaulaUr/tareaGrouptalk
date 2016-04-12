package eetac.upc.eetac.dsa.grouptalk.dao;

/**
 * Created by pauli on 12/04/2016.
 */
public interface RespuestaDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREAR_RESPUESTA = "insert into respuesta (id, temaid, userid, respuesta) values (UNHEX(?), UNHEX(?), UNHEX(?), ?)";
    public final static String EDITAR_RESPUESTA = "update respuesta set respuesta=? where id=unhex(?)";
    public final static String ELIMINAR_RESPUESTA = "delete from respuesta where id=unhex(?)";
    public final static String OBTENER_COLECCION_RESPUESTAS_APARTIR_USERID = "select hex(r.id) as id, hex(r.temaid) as temaid, hex(r.userid) as userid, r.respuesta from respuesta r where userid=unhex(?)";
    public final static String OBTENER_RESPUESTA_APARTIR_ID = "select hex(r.id) as id, hex(r.temaid) as temaid, hex(r.userid) as userid, r.respuesta from respuesta r where id=unhex(?)";

}
