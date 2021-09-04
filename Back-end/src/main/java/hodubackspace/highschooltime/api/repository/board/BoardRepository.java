package hodubackspace.highschooltime.api.repository.board;

import hodubackspace.highschooltime.api.repository.CommonFunctionRepository;
import hodubackspace.highschooltime.domain.Board;
import hodubackspace.highschooltime.domain.BoardGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
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

    public Optional<Board> findOneWithMember(Long id) {
        return Optional.ofNullable(em.createQuery("select b from Board b join fetch b.member m where b.id=:id", Board.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    public Optional<Board> findOneWithCommentsMember(BoardGroup boardGroup, Long id) {
        return Optional.ofNullable(em.createQuery("select b from Board b join fetch b.member join b.boardGroup bg " +
                        "where b.id = :id and bg=:boardGroup", Board.class)
                .setParameter("id", id)
                .setParameter("boardGroup", boardGroup)
                .getSingleResult());
    }

    @Override
    public List<Board> findAll() {
        return null;
    }

    public List<Board> findAllTest(String boardName, String whereWrite, int page) {
        return em.createQuery("select b from Board b join b.boardGroup bg on bg.boardName=:boardName where b.whereWrite=:whereWrite")
                .setParameter("boardName",boardName)
                .setParameter("whereWrite",whereWrite)
                .setFirstResult((page-1) * 10)
                .setMaxResults(10)
                .getResultList();
    }

}
