//import the classes
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
//create the class UserDAOMySQL that inherits from the UserDao interface
public class UsuarioDAOMySQL implements UsuarioDAO {
//create the create user method
    @Override
    public int create(Usuario usuario) throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //we insert the data
        String query = "INSERT INTO libros(id, ISBN, autor, genero, cantidad) VALUES (?,?,?,?,?);";
        PreparedStatement pstm = con.prepareStatement(query);

        pstm.setInt(1, usuario.getId());
        pstm.setString(2, usuario.getISBN());
        pstm.setString(3, usuario.getAutor());
        pstm.setString(4, usuario.getGenero());
        pstm.setString(5, usuario.getCantidad());

        int res = pstm.executeUpdate();

        return res;
    }
//create the create read method
    @Override
    public Usuario read(String id) throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //select the record we want
        String query = "SELECT * FROM libros WHERE id = ?";

        Usuario ex = null;
        PreparedStatement pstm = con.prepareStatement(query);
        pstm.setString(1, id);
        ResultSet rs = pstm.executeQuery();
         if(rs.next()){
            int idRs = rs.getInt(1);
            String ISBN = rs.getString(2);
            String aut = rs.getString(3);
            String genero = rs.getString(4);
            String can = rs.getString(5);
            ex = new Usuario(idRs, ISBN, aut, genero, can);
         }
         return ex;
    }
//create the readAll user method
    @Override
    public ArrayList<Usuario> readAll() throws SQLException {
        //connect to the database
        Connection con = ConexionDB.getConnection();
        //create an arraylist
        ArrayList<Usuario> usuarios = new ArrayList<>();
        //select all records from the database
        String sql = "SELECT * FROM libros";
        Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String ISBN = rs.getString("ISBN");
            String autor = rs.getString("autor");
            String genero = rs.getString("genero");
            String cantidad = rs.getString("cantidad");

            Usuario aux = new Usuario(id, ISBN, autor, genero, cantidad);

            usuarios.add(aux);
        }
        return usuarios;
    }
//create the update user method
@Override
public int update(Usuario usuario) throws SQLException {
    //connect to the database
    Connection con = ConexionDB.getConnection();
    //create the instruction to select the data from the users table
    String query = "UPDATE libros SET ISBN=?, autor=?, genero=?, cantidad=? WHERE id=?;";
    PreparedStatement pstm = con.prepareStatement(query);

    pstm.setString(1, usuario.getISBN());
    pstm.setString(2, usuario.getAutor());
    pstm.setString(3, usuario.getGenero());
    pstm.setString(4, usuario.getCantidad());
    pstm.setInt(5, usuario.getId());

    int res = pstm.executeUpdate();

    return res;
}
//create the delete user method
@Override
public int delete(int clave) throws SQLException {
    //connect to the database
    Connection con = ConexionDB.getConnection();
    //create the instruction that deletes a record from the users table
    String query = "DELETE FROM libros WHERE id=?;";
    PreparedStatement pstm = con.prepareStatement(query);

    pstm.setInt(1, clave);

    int res = pstm.executeUpdate();

    return res;
}

    
    
}
