package vlad.dao;

import com.zaxxer.hikari.HikariDataSource;
import vlad.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserJdbcDao implements Dao<User> {

    private final HikariDataSource hDataSource;

    public UserJdbcDao(HikariDataSource hDataSource) {
        this.hDataSource = hDataSource;
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

    public Optional<User> retrieveById(Long id) {

        try (Connection connection = hDataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setSchema("tinder");
            PreparedStatement st = connection.prepareStatement("SELECT users.name from users " +
                    "WHERE users.id = ? ");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            connection.commit();
            if (!rs.isBeforeFirst()) return Optional.empty();
            String email = null;
            String name = null;
            String login = null;
            String avatar = null;
            while (rs.next()) {
                id = rs.getLong("id");
                name = rs.getString("name");
                login = rs.getString("login");
                email = rs.getString("email");
                avatar = rs.getString("avatar");

            }
            return Optional.of(new User(id, name, email, login, avatar));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<User> retrieveByEmail(String email) {
        try (Connection connection = hDataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setSchema("tinder");
            PreparedStatement st = connection.prepareStatement("SELECT users.id, users.name FROM users WHERE users.login = ?");
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            connection.commit();
            if (!rs.isBeforeFirst()) return Optional.empty();
            Long id = null;
            String name = null;
            String login = null;
            String avatar = null;
            while (rs.next()) {
                id = rs.getLong("id");
                name = rs.getString("name");
                login = rs.getString("login");
                avatar = rs.getString("avatar");

            }
            return Optional.of(new User(id, name, email, login, avatar));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.of(null);
    }

    public Optional<User> retrieveByLogin(String login) {
        try (Connection connection = hDataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setSchema("tinder");
            PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE users.login = ?");
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            connection.commit();
//            System.out.println("rs.isBeforeFirst()= " + rs.isBeforeFirst());
            if (!rs.isBeforeFirst()) return Optional.empty();
            Long id = null;
            String name = null;
            String email = null;
            String avatar = null;
            while (rs.next()) {
                id = rs.getLong("id");
                name = rs.getString("name");
                email = rs.getString("login");
                avatar = rs.getString("avatar");

            }
            return Optional.of(new User(id, name, email, login, avatar));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Optional.of(null);
    }

    public List<Long> findLikedUserIds(Long currentUserId) {
        List<Long> likedUserIds = new ArrayList<>();
        try (Connection connection = hDataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setSchema("tinder");
            PreparedStatement st = connection.prepareStatement("SELECT likes.liked_who from likes where " +
                    "liked_by = ?");
            st.setLong(1, currentUserId);
            connection.commit();
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) return likedUserIds;
            while (rs.next()) {
                likedUserIds.add(rs.getLong("liked_who"));
            }
            return likedUserIds;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return likedUserIds;
    }

    public List<User> findSelectedUsers(List<Long> likedUserIds) {
        // ???????????????????? ???????????? id ???????? List<Long> ?????? ???????????????? ?? ???? ???????? ???????? SQL- ???????????????????????? ?????????????? Array ?? ?????????? SQL int:
        // ?????????????? ???????????? ??????????-Long ?? Integer:
        List<Integer> idListInt = likedUserIds.stream().map(Math::toIntExact).collect(Collectors.toList());
        // ?????????????? ?????????????? ???????????? idArray ???????? Integer:
        Integer[] idArray = new Integer[likedUserIds.size()];
        // ???????????? ?????????????????? ???????????? ???? ???????????? List<Integer> idListInt ???? ??????????-?????????????????? ???????????? idArray:
        idListInt.toArray(idArray);

        List<User> likedUsers = new ArrayList<>();
        try (Connection connection = hDataSource.getConnection()) {
            connection.setAutoCommit(false);
            connection.setSchema("tinder");
            // ????????????????: ?????????? ???????????????????? ?????????????????????? ?????????????? sql.Array (idArray) ???????????? '?',
            // ???????????? 'WHERE IN' ?????? Postgres ?????????? ??????????????????????  '= ANY (?)'
            // ??????  =ANY:    https://html5css.ru/sql/sql_any_all.php
            PreparedStatement st = connection.prepareStatement("SELECT * from users where " +
                    "users.id = ANY (?)");

            // ???????????? ?????????????????? ?????????????? ???????????? idArray ?? SQL-????????????: java.sql.Array
            Array stIdArray = connection.createArrayOf("integer", idArray);
            st.setArray(1, stIdArray);

            connection.commit();
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) return likedUsers;
            long id;
            String email;
            String name;
            String login;
            String avatar;
            while (rs.next()) {
                id = rs.getLong("id");
                name = rs.getString("name");
                login = rs.getString("login");
                email = rs.getString("email");
                avatar = rs.getString("avatar");
                likedUsers.add(new User(id, name, email, login, avatar));
            }
            return likedUsers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return likedUsers;
    }

    public void createUser(String name, String login, String email, String avatar){
        System.out.println("in createUser!");
        try(Connection connection = hDataSource.getConnection()){
            connection.setAutoCommit(false);
            connection.setSchema("tinder");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, email, login, avatar)" +
                    "VALUES (?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, login);
            ps.setString(4, avatar);
            ps.execute();
            connection.commit();

        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public List<User> findAll(int limit, int offset) {
        List<User> likedUsers = new ArrayList<>();

        try (Connection connection = hDataSource.getConnection()) {
            connection.setSchema("tinder");
            PreparedStatement st = connection.prepareStatement("SELECT * FROM users LIMIT ? OFFSET ?");
            st.setInt(1, limit);
            st.setInt(2, offset);

            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) return likedUsers;
            long id;
            String email;
            String name;
            String login;
            String avatar;
            while (rs.next()) {
                id = rs.getLong("id");
                name = rs.getString("name");
                login = rs.getString("login");
                email = rs.getString("email");
                avatar = rs.getString("avatar");
                likedUsers.add(new User(id, name, email, login, avatar));
            }
            return likedUsers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return likedUsers;
    }


  }
