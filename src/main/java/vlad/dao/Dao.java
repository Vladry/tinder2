package vlad.dao;

import java.util.Optional;

public interface Dao<T> {
    Optional<T> retrieveById(Long id);
    byte[] getImage(Long id);

    Optional<T> retrieveByEmail(String email);
    Optional<T> retrieveByLogin(String login);
}
