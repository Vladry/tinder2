package vlad.dao;

import com.zaxxer.hikari.HikariDataSource;
import vlad.NoDataFoundException;
import vlad.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserJdbcDao implements Dao<User> {

    private final HikariDataSource hDataSource;

    public UserJdbcDao(HikariDataSource hDataSource) {
        this.hDataSource = hDataSource;
    }


    public Optional<User> retrieveById(Long id) {

        try (Connection connection = hDataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement st = connection.prepareStatement("SELECT users.name from users " +
                    "WHERE users.id = ? ");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            connection.commit();
            if (!rs.isBeforeFirst()) return Optional.empty();
            String name = "";
            while (rs.next()) {
                name = rs.getString("name");
            }
            return Optional.of(new User(id, name));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<User> retrieveByEmail(String email) {
        try (Connection connection = hDataSource.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement st = connection.prepareStatement("SELECT users.id, users.name FROM users WHERE users.login = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            connection.commit();
            if (!rs.isBeforeFirst()) return Optional.empty();
            Long id = null;
            String name = null;
            String login = null;
            while (rs.next()) {
                id = rs.getLong("id");
                name = rs.getString("name");
                login = rs.getString("login");

            }
        return Optional.of(new User(id, name, email, login));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.of(null);
    }

    public Optional<User> retrieveByLogin(String login) {
        try (Connection connection = hDataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setSchema("tinder");
            System.out.println("connection.getCatalog()=  " + connection.getCatalog());
            System.out.println("connection.getSchema()=  " + connection.getSchema());
            PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE users.login = ?");
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            connection.commit();
            System.out.println("rs.isBeforeFirst()= " + rs.isBeforeFirst());
            if (!rs.isBeforeFirst()) return Optional.empty();
            Long id = null;
            String name = null;
            String email = null;
            while (rs.next()) {
                id = rs.getLong("id");
                name = rs.getString("name");
                email = rs.getString("login");

            }
            return Optional.of(new User(id, name, email, login));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.of(null);
    }



    @Override
    public byte[] getImage(Long userId) {
        Connection connection = null;
        try {
            connection = hDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT image FROM users.users WHERE id = ?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBytes("image");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

}
