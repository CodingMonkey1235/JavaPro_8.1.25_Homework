package org.example;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDao {
    private final HikariDataSource dataSource;
    private final String table_name = "service_user";

    @Autowired
    public UserDao(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<User> find(long id) throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            String sql = String.format("select id, username from %s where id = %s", table_name, id);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("username"));
                statement.close();
                connection.close();
                return Optional.of(user);
            } else {
                statement.close();
                connection.close();
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<User> findAll() throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            String sql = String.format("select * from %s", table_name);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getLong("id"), resultSet.getString("username"));
                users.add(user);
            }
            statement.close();
            connection.close();
            return users;

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }

    public Optional<User> create(String username) throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            String sql = String.format("insert into %s(username) values('%s')", table_name, username);
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int row = statement.executeUpdate();
            if (row > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    statement.close();
                    connection.close();
                    return Optional.of(new User(id, username));
                } else {
                    statement.close();
                    connection.close();
                    return Optional.empty();
                }
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            String sql = String.format("update %s set username = '%s' where id = %s", table_name, user.getUsername(), user.getId());
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User user) throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            String sql = String.format("delete from %s where id = %s", table_name, user.getId());
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printResultSet(ResultSet rs) throws SQLException {
        // Get metadata to retrieve column names and count
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Print column headers
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t\t"); // Use tabs for spacing
        }
        System.out.println(); // New line after headers

        // Print data rows
        while (rs.next()) { // Iterate through each row
            for (int i = 1; i <= columnCount; i++) {
                // Retrieve data as String to handle various data types
                System.out.print(rs.getString(i) + "\t\t");
            }
            System.out.println(); // New line after each row
        }
    }
}
