//we import the libraries
import java.sql.SQLException;
import java.util.ArrayList;
//create the DAO user interface
public interface UsuarioDAO{
    //create the methods
    public int create(Usuario usuario) throws SQLException;
    public Usuario read(String id) throws SQLException;
    public int update(Usuario usuario) throws SQLException;
    public int delete(int clave) throws SQLException;
    public ArrayList<Usuario> readAll() throws SQLException;
}
