import java.sql.*;

public class InMemoryDB {
    private static volatile InMemoryDB instance;

    private final Connection connection;

    public InMemoryDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:mem:test");

        init();
    }

    public static InMemoryDB getInstance() throws SQLException {
        InMemoryDB localInstance = instance;

        if (localInstance == null) {
            synchronized (InMemoryDB.class) {
                localInstance = instance;

                if (localInstance == null) {
                    instance = localInstance = new InMemoryDB();
                }
            }
        }

        return localInstance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void init() throws SQLException {
        System.out.println("Connected to H2 in-memory database.");

        String sqlCreateUser = "Create table users (ID int primary key, name varchar(50), password varchar(25), email varchar(50))";

        Statement statement = connection.createStatement();

        statement.execute(sqlCreateUser);

        System.out.println("Created table users.");

        String sqlInsertUser = "Insert into users (ID, name, password, email) values (1, 'Petya', '12345678', 'petya@yandex.ru')";
        int rows = statement.executeUpdate(sqlInsertUser);
        if (rows > 0) {
            System.out.println("User Petya added");
        }

        sqlInsertUser = "Insert into users (ID, name, password, email) values (2, 'Kolya', '12345678', 'kolya@yandex.ru')";
        rows = statement.executeUpdate(sqlInsertUser);
        if (rows > 0) {
            System.out.println("User Kolya added");
        }

        sqlInsertUser = "Insert into users (ID, name, password, email) values (3, 'Sveta', '12345678', 'sveta@yandex.ru')";
        rows = statement.executeUpdate(sqlInsertUser);
        if (rows > 0) {
            System.out.println("User Sveta added");
        }
    }
}
