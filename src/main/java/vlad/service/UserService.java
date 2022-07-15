package vlad.service;

import vlad.dao.UserJdbcDao;
import vlad.domain.User;

import java.util.Optional;

public class UserService {
    private final UserJdbcDao userDao;

    public UserService(UserJdbcDao userDao) {
        this.userDao = userDao;
    }

    public Optional<User> findById(Long id) {
        return userDao.retrieveById(id);
    }

    public byte[] getImage(Long id) {
        return userDao.getImage(id);
    }

}
