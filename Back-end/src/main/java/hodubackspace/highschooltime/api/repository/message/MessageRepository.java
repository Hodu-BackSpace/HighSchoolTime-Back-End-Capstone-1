package hodubackspace.highschooltime.api.repository.message;

import hodubackspace.highschooltime.api.repository.CommonFunctionRepository;
import hodubackspace.highschooltime.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MessageRepository implements CommonFunctionRepository<Message,Long> {

    private final EntityManager em;

    @Override
    public void save(Message entity) {
        em.persist(entity);
    }

    @Override
    public Optional<Message> findOne(Long id) {
        return Optional.ofNullable(em.find(Message.class, id));
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

}
