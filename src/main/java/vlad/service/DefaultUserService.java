package vlad.service;

import vlad.dao.User;
import vlad.dao.UserDao;

import java.io.InputStream;
import java.util.List;

public class DefaultUserService implements UserService {
    private UserDao userDao;

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean create(User user) {
        return userDao.create(user);
    }

    @Override
    public User read(Long id) {
        return userDao.read(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public boolean delete(long id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByLoginPass(String login, String password) {
        return userDao.findByLoginPass(login, password);
    }

    @Override
    public void uploadImage(Long id, InputStream image) {
        userDao.uploadImage(id, image);
    }

    @Override
    public byte[] getImage(Long id) {
        return userDao.getImage(id);
    }
}
