package hodubackspace.highschooltime.api.repository.comment;

import hodubackspace.highschooltime.api.advice.exception.found.NotFoundCommentException;
import hodubackspace.highschooltime.api.repository.CommonFunctionRepository;
import hodubackspace.highschooltime.domain.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentRepository implements CommonFunctionRepository<Comment,Long> {

    private final EntityManager em;


    @Override
    public void save(Comment entity) {
        em.persist(entity);
    }

    @Override
    public Optional<Comment> findOne(Long id) {
        return Optional.ofNullable(em.find(Comment.class,id));
    }

    public Optional<Comment> findOneWithMember(Long id) {
        try {
            return Optional.ofNullable(em.createQuery("select c from Comment c join fetch c.member m where c.id=:id", Comment.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (NoResultException e) {
            throw new NotFoundCommentException();
        }
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    public void remove(Comment entity){
        em.remove(entity);
    }
}
