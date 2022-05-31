import java.sql.SQLException;

public class DataMapperClient {
    public static void main(String[] args) throws SQLException {
        UserMapper userMapper = new UserMapper();

        // Заберет из базы и добавит в map
        userMapper.findById(3).ifPresentOrElse(System.out::println, () -> System.out.println("User not found"));

        // Заберет из базы и добавит в map
        userMapper.findById(1).ifPresentOrElse(System.out::println, () -> System.out.println("User not found"));

        // Отдвст из map
        userMapper.findById(3).ifPresentOrElse(System.out::println, () -> System.out.println("User not found"));
    }
}
