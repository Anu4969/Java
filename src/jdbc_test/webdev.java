package jdbc_test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class webdev {
/* public static void main(String[] args) {
	String url = "jdbc:mysql//localhost:3306/student";
	String username = "root";
	String password ="";
	String q1= "select * from student";
	Class forName= ("com.jdbc.mysql.driver");
	Connection con = driverManager. getConnection(url,username,password);
	statement st = con.createStatement;
	resultSet rs = st.executeQuery
}*/

    public static void main(String[] args) {
        // JDBC connection parameters
        String jdbcUrl = "jdbc:mysql://localhost:3306/student";
        String username = "root";
        String password = "Anu@uttara";

        // JDBC variables
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load the JDBC driver (for MySQL, you can use "com.mysql.cj.jdbc.Driver")
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a SQL statement
            statement = connection.createStatement();

            // Execute a simple query
            String sqlQuery = "SELECT * FROM student";
            resultSet = statement.executeQuery(sqlQuery);

            // Process the results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // Process other columns as needed
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC resources in the reverse order of their creation
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

