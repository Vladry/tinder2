package com.danit.dao;

//import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.postgresql.ds.PGPoolingDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao {
//    private MysqlConnectionPoolDataSource source;
    private PGPoolingDataSource source;

/*    public UserJdbcDao() {
        try {
            source = new MysqlConnectionPoolDataSource();
            source.setServerName("localhost");
            source.setPort(3306);
            source.setDatabaseName("users");
            source.setUser("root");
            source.setPassword("root");
            source.setAllowMultiQueries(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/

    public UserJdbcDao() {
        source = new PGPoolingDataSource();
        source.setServerName("ec2-35-170-239-232.compute-1.amazonaws.com");
        source.setDatabaseName("demcjnf96hduah");
        source.setUser("disxdmkwvmnzbs");
        source.setPassword("9a89674865e3e5c673e5a63128de5bd2b4f5d4653d9a1b22d2694d812202e1ec");
        source.setMaxConnections(10);
    }

    @Override
    public boolean create(User user) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users.users(name, age, group_id, login, password) VALUES (?, ?, ?, ?, ?)");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getAge());
            preparedStatement.setLong(3, user.getGroupId());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());


            int executionResult = preparedStatement.executeUpdate();
            connection.commit();

            return executionResult > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public User read(Long userId) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users.users WHERE id = ?");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString(2);
                int age = resultSet.getInt("age");
                Long groupId = resultSet.getLong("group_id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                return new User(id, name, age, groupId, login, password);
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

    @Override
    public void update(User user) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users.users SET name=?, age=?, group_id=?, login=?, password=? WHERE id=?");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setLong(2, user.getAge());
            preparedStatement.setLong(3, user.getGroupId());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setLong(6, user.getId());


            int executionResult = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString(2);
                int age = resultSet.getInt("age");
                Long groupId = resultSet.getLong("group_id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                users.add(new User(id, name, age, groupId, login, password));
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
        return users;
    }
    @Override
    public User findByLoginPass(String loginUser, String passwordUser) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users.users WHERE login=? AND password=?");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setString(1, loginUser);
            preparedStatement.setString(2, passwordUser);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString(2);
                int age = resultSet.getInt("age");
                Long groupId = resultSet.getLong("group_id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                return new User(id, name, age, groupId, login, password);
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

    @Override
    public void uploadImage(Long id, InputStream image) {
        Connection connection = null;
        try {
            connection = source.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update users.users set image = ? where id=?");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 3");
            preparedStatement.setBinaryStream(1, image);
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
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
    }

    @Override
    public byte[] getImage(Long userId) {
        Connection connection = null;
        try {
            connection = source.getConnection();
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
