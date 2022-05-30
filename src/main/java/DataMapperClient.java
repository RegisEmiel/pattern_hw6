import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataMapperClient {
    public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:h2:mem:test";

        Connection connection = DriverManager.getConnection(jdbcURL);

        System.out.println("Connected to H2 in-memory database.");

        String sql = "Create table students (ID int primary key, name varchar(50))";

        Statement statement = connection.createStatement();

        statement.execute(sql);

        System.out.println("Created table students.");

        sql = "Insert into students (ID, name) values (1, 'Nam Ha Minh')";

        int rows = statement.executeUpdate(sql);

        if (rows > 0) {
            System.out.println("Inserted a new row.");
        }

        connection.close();
    }
}
