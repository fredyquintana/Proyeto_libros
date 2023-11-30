import java.sql.SQLException;
import java.util.ArrayList;

public interface  UsuarioBibliotecaDAO {
    //create the methods
    public int create(UsuarioBiblioteca usuarioBiblioteca) throws SQLException;
    public UsuarioBiblioteca read(String id) throws SQLException;
    public int update(UsuarioBiblioteca usuarioBiblioteca) throws SQLException;
    public int delete(int clave) throws SQLException;
    public ArrayList<UsuarioBiblioteca> readAll() throws SQLException;
}
