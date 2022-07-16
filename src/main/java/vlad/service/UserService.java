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
    public Optional<User> findByEmail(String email){return userDao.retrieveByEmail(email);}
    public Optional<User> findByLogin(String login){return userDao.retrieveByLogin(login);}

    public byte[] getImage(Long id) {
        return userDao.getImage(id);
    }

}
