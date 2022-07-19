package vlad.service;

import vlad.dao.UserJdbcDao;
import vlad.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public List<Long> findLikedUserIds(Long currentUserId){return userDao.findLikedUserIds(currentUserId);};
    public List<User> findSelectedUsers(List<Long> likedUserIds){return userDao.findSelectedUsers(likedUserIds);};
    public List<Long> findUnlikedUserIds(Long currentUserId){return userDao.findUnlikedUserIds(currentUserId);};

    public void createUser(String name, String login, String email, String avatar){
        userDao.createUser(name, login, email, avatar);
    }

    public byte[] getImage(Long id) {
        return userDao.getImage(id);
    }

}
