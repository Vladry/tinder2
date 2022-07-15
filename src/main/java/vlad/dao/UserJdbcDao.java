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
            if(!rs.isBeforeFirst()) return Optional.empty();
            String name = "";
            while(rs.next()){
               name = rs.getString("name");
            }
            return Optional.of(new User(id, name));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }


    @Override
    public byte[] getImage(Long userId) {
        Connection connection = null;
        try {
            connection = hDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT image FROM users.users WHERE id = ?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
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
