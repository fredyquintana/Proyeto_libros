//import the classes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//create the class UserDAOMySQL that inherits from the UserDao interface
public class PrestamoDAOMySQL implements PrestamoDAO {
//create the create user method
    @Override
    public int create(Prestamo prestamo) throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //we insert the data
        String query = "INSERT INTO prestamos(id,id_libro,fecha_entrega,fecha_devolucion, cantidad) VALUES (?,?,?,?,?);";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1, prestamo.getId());
        pstm.setString(2, prestamo.getId_libro());
        pstm.setString(3, prestamo.getFecha_entrega());
        pstm.setString(4, prestamo.getFecha_devolucion());
        pstm.setString(5, prestamo.getCantidad());

        int res = pstm.executeUpdate();

        return res;
    }
//create the create read method
    @Override
    public Prestamo read(String id) throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //select the record we want
        String query = "SELECT * FROM prestamos WHERE id = ?";

        Prestamo ex = null;
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
         if(rs.next()){
            int idRs = rs.getInt(1);
            String id_libro= rs.getString(2);
            String fecha_entrega= rs.getString(3);
            String fecha_devolucion= rs.getString(4);
            String can = rs.getString(5);
            ex = new Prestamo(idRs, id_libro, fecha_entrega, fecha_devolucion, can);
         }
         return ex;
    }
//create the readAll user method
    @Override
    public ArrayList<Prestamo> readAll() throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //create an arraylist
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        //select all records from the database
        String sql = "SELECT * FROM prestamos";
        Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String id_libro = rs.getString("id_libro");
            String fecha = rs.getString("fecha_entrega");
            String devolucion = rs.getString("fecha_devolucion");
            String cantidad = rs.getString("cantidad");

            Prestamo aux = new Prestamo(id, id_libro, fecha, devolucion, cantidad);

            prestamos.add(aux);
        }
        return prestamos;
    }
//create the update user method
@Override
public int update(Prestamo prestamo) throws SQLException {
    //connect to the database
    Connection con = ConexionDB.getConnection();
    //create the instruction to select the data from the users table
    String query = "UPDATE prestamos SET id_libro=?, fecha_entrega=?, fecha_devolucion=?, cantidad=? WHERE id=?;";
    PreparedStatement pstm = con.prepareStatement(query);

    pstm.setString(1, prestamo.getId_libro());
    pstm.setString(2, prestamo.getFecha_entrega());
    pstm.setString(3, prestamo.getFecha_devolucion());
    pstm.setString(4, prestamo.getCantidad());
    pstm.setInt(5, prestamo.getId());

    int res = pstm.executeUpdate();

    return res;
}
//create the delete user method
@Override
public int delete(int clave) throws SQLException {
    //connect to the database
    Connection con = ConexionDB.getConnection();
    //create the instruction that deletes a record from the users table
    String query = "DELETE FROM prestamos WHERE id=?;";
    PreparedStatement pstm = con.prepareStatement(query);

    pstm.setInt(1, clave);

    int res = pstm.executeUpdate();

    return res;
}

    
    
}
