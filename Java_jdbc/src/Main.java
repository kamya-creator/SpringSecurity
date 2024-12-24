import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/eazybank"; // Replace with your DB URL
        String username = "root"; // Replace with your username
        String password = "root"; // Replace with your password

        // SQL query to fetch table names
        String query = "SHOW TABLES";

        // Initialize connection and statement
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("Tables in the database:");

            // Iterate through the result set to print table names
            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                System.out.println(tableName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while connecting to the database or fetching tables.");
        }
    }
}
