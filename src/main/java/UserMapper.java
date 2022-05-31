import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {

    private final Connection connection;

    private final Map<Integer, User> userMap;

    public UserMapper() throws SQLException {
        userMap = new HashMap<>();

        connection = InMemoryDB.getInstance().getConnection();
    }

    public Optional<User> findById(Integer id) throws SQLException {
        if (userMap.containsKey(id)) {
            return Optional.of(userMap.get(id));
        }

        PreparedStatement findUser = connection.prepareStatement("select * from users where id = ?;");
        findUser.setInt(1, id);

        ResultSet rs = findUser.executeQuery();

        if (rs.next()) {
            User user = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));

            userMap.put(id, user);

            return Optional.of(user);
        }

        return Optional.empty();
    }
}
