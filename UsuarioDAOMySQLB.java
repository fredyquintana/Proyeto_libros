//import the classes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//create the class UserDAOMySQL that inherits from the UserDao interface
public class UsuarioDAOMySQLB implements UsuarioBibliotecaDAO {
//create the create user method
    @Override
    public int create(UsuarioBiblioteca usuarioBiblioteca) throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //we insert the data
        String query = "INSERT INTO usuarios(id, nombre, apellido, correo, direccion) VALUES (?,?,?,?,?);";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1,usuarioBiblioteca.getId());
        pstm.setString(2, usuarioBiblioteca.getNombre());
        pstm.setString(3,usuarioBiblioteca.getApellido());
        pstm.setString(4, usuarioBiblioteca.getCorreo());
        pstm.setString(5, usuarioBiblioteca.getDireccion());

        int res = pstm.executeUpdate();

        return res;
    }
//create the create read method
    @Override
    public UsuarioBiblioteca read(String id) throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //select the record we want
        String query = "SELECT * FROM usuarios WHERE id = ?";

        UsuarioBiblioteca ex = null;
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
         if(rs.next()){
            int idRs = rs.getInt(1);
            String nombre = rs.getString(2);
            String apellido = rs.getString(3);
            String correo = rs.getString(4);
            String direccion = rs.getString(5);
            ex = new UsuarioBiblioteca(idRs, nombre, apellido, correo, direccion);
         }
         return ex;
    }
//create the readAll user method
    @Override
    public ArrayList<UsuarioBiblioteca> readAll() throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //create an arraylist
        ArrayList<UsuarioBiblioteca> listaUsuariosbiblioteca = new ArrayList<>();
        //select all records from the database
        String sql = "SELECT * FROM usuarios";
        Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String correo = rs.getString("correo");
            String direccion = rs.getString("direccion");

            UsuarioBiblioteca aux = new UsuarioBiblioteca(id, nombre, apellido, correo, direccion);

            listaUsuariosbiblioteca.add(aux);
        }
        return listaUsuariosbiblioteca;
    }
//create the update user method
@Override
public int update(UsuarioBiblioteca listaUsuariosbiblioteca) throws SQLException {
    //connect to the database
    Connection con = ConexionDB.getConnection();
    //create the instruction to select the data from the users table
    String query = "UPDATE usuarios SET nombre=?, apellido=?, correo=?, direccion=? WHERE id=?;";
    PreparedStatement pstm = con.prepareStatement(query);

    pstm.setString(1, listaUsuariosbiblioteca.getNombre());
    pstm.setString(2, listaUsuariosbiblioteca.getApellido());
    pstm.setString(3, listaUsuariosbiblioteca.getCorreo());
    pstm.setString(4, listaUsuariosbiblioteca.getDireccion());
    pstm.setInt(5, listaUsuariosbiblioteca.getId());

    int res = pstm.executeUpdate();

    return res;
}
//create the delete user method
@Override
public int delete(int clave) throws SQLException {
    //connect to the database
    Connection con = ConexionDB.getConnection();
    //create the instruction that deletes a record from the users table
    String query = "DELETE FROM usuarios WHERE id=?;";
    PreparedStatement pstm = con.prepareStatement(query);

    pstm.setInt(1, clave);

    int res = pstm.executeUpdate();

    return res;
}

    
    
}