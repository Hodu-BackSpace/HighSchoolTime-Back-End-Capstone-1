package hodubackspace.highschooltime.api.repository.boardgroup;

import hodubackspace.highschooltime.api.repository.CommonFunctionRepository;
import hodubackspace.highschooltime.domain.BoardGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardGroupRepository implements CommonFunctionRepository<BoardGroup, Long> {

    private final EntityManager em;

    @Override
    public void save(BoardGroup entity) {

    }

    @Override
    public Optional<BoardGroup> findOne(Long aLong) {
        return Optional.empty();
    }

    public Optional<BoardGroup> findOneByBoardName(String boardName) {
        return Optional.ofNullable(em.createQuery("select bg from BoardGroup bg where bg.boardName=:boardName",BoardGroup.class)
                .setParameter("boardName",boardName)
                .getSingleResult());
    }

    @Override
    public List<BoardGroup> findAll() {
        return null;
    }
}
