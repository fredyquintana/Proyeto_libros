//we import the libraries
import java.sql.SQLException;
import java.util.ArrayList;
//create the DAO user interface
public interface PrestamoDAO{
    //create the methods
    public int create(Prestamo prestamo) throws SQLException;
    public Prestamo read(String id) throws SQLException;
    public int update(Prestamo prestamo) throws SQLException;
    public int delete(int clave) throws SQLException;
    public ArrayList<Prestamo> readAll() throws SQLException;
}
