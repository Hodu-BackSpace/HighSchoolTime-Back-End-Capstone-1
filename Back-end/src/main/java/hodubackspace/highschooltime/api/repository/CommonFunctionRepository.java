package hodubackspace.highschooltime.api.repository;

import java.util.List;
import java.util.Optional;

public interface CommonFunctionRepository<T,ID> {

    void save(T entity);
    Optional<T> findOne(ID id);
    List<T> findAll();
}
