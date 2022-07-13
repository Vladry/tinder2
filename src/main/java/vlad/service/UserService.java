package vlad.service;

import vlad.dao.User;

import java.io.InputStream;
import java.util.List;

public interface UserService {
    boolean create(User user);
    User read(Long id);
    void update(User user);
    boolean delete(long id);
    List<User> findAll();
    User findByLoginPass(String login,String password);
    void uploadImage(Long id, InputStream image);
    byte[] getImage(Long id);
}
