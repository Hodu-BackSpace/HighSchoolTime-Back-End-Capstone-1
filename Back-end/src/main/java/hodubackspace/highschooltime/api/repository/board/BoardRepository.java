package hodubackspace.highschooltime.api.repository.board;

import hodubackspace.highschooltime.api.repository.CommonFunctionRepository;
import hodubackspace.highschooltime.domain.Board;
import hodubackspace.highschooltime.domain.BoardGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardRepository implements CommonFunctionRepository<Board, Long> {

    private final EntityManager em;
    @Override
    public void save(Board entity) {
        em.persist(entity);
    }
    public Long saveReturnId(Board entity) {
        em.persist(entity);
        return entity.getId();
    }

    @Override
    public Optional<Board> findOne(Long id) {
        return Optional.ofNullable(em.find(Board.class, id));
    }

    public Optional<Board> findOneWithComments(BoardGroup boardGroup, Long id) {
        return Optional.ofNullable(em.createQuery("select distinct b from Board b left join fetch b.comments join b.boardGroup bg " +
                        "where b.id = :id and bg=:boardGroup", Board.class)
                .setParameter("id", id)
                .setParameter("boardGroup", boardGroup)
                .getSingleResult());
    }

    @Override
    public List<Board> findAll() {
        return null;
    }

}
