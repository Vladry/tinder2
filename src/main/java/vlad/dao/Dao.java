package vlad.dao;

import java.util.Optional;

public interface Dao<T> {
    Optional<T> retrieveById(Long id);
    byte[] getImage(Long id);
}
