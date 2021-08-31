package hodubackspace.highschooltime.api.repository.member;

import hodubackspace.highschooltime.api.advice.exception.LoginEmailNotFoundMemberException;
import hodubackspace.highschooltime.api.repository.CommonFunctionRepository;
import hodubackspace.highschooltime.domain.Member;
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
public class MemberRepository implements CommonFunctionRepository<Member,Long> {

    private final EntityManager em;

    @Override
    public void save(Member entity) {
        em.persist(entity);
    }

    @Override
    public Optional<Member> findOne(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public Optional<Member> findOneByEmail(String email) {
        try {
            return Optional.ofNullable(em.createQuery("select m from Member m where m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getSingleResult());
        } catch (NoResultException e) {
            throw new LoginEmailNotFoundMemberException("Not Found Login Member!");
        }
    }

    public Optional<String> findOneByNickName(String nickName) {
        try {
            return Optional.ofNullable(em.createQuery("select m.nickName from Member m where m.nickName = :nickName", String.class)
                    .setParameter("nickName", nickName)
                    .getSingleResult());
        } catch (NoResultException e) {
            throw e;
        }
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }

    public List<Member> findAllByEmail(String email) {
            return em.createQuery("select m from Member m where m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getResultList();

    }
}
