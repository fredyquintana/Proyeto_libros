import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Singleton class for managing database connections
public class ConexionDB {

    // Database connection parameters
    private static final String url = "jdbc:mysql://localhost:3306/bibliotequita";
    private static final String user = "root";
    private static final String password = "";

    // Private constructor to prevent instantiation
    public ConexionDB() {
    }

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    // Close a database connection
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    // Close a Statement object
    public static void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }

    // Close a PreparedStatement object
    public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.close();
    }

    // Close a ResultSet object
    public static void closeResultSet(ResultSet resultSet) throws SQLException {
        resultSet.close();
    }
}
