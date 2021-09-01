package hodubackspace.highschooltime.api.repository.friend;

import hodubackspace.highschooltime.api.repository.CommonFunctionRepository;
import hodubackspace.highschooltime.domain.Friend;
import hodubackspace.highschooltime.domain.status.FriendStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FriendRepository implements CommonFunctionRepository<Friend, Long> {

    private final EntityManager em;

    @Override
    public void save(Friend entity) {
        em.persist(entity);
    }

    @Override
    public Optional<Friend> findOne(Long id) {
        return Optional.ofNullable(em.find(Friend.class, id));
    }

    public Optional<Friend> findOneWithMemberIdFriendId(Long friendId, Long memberId) {
        return Optional.ofNullable(em.createQuery("select f from Friend f " +
                        "join f.member m on m.id=:friendId " +
                        "where f.friendMemberId=:memberId", Friend.class)
                .setParameter("memberId", memberId)
                .setParameter("friendId", friendId)
                .getSingleResult());
    }

    @Override
    public List<Friend> findAll() {
        return Collections.emptyList();
    }

    public List<Friend> findAllByMemberId(Long memberId) {
        return em.createQuery("select f from Friend f join f.member m where m.id=:memberId and f.friendStatus=:status", Friend.class)
                .setParameter("status",FriendStatus.COMPLETE)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<Friend> findBeAddedAllByMemberId(Long memberId) {
        return em.createQuery("select f from Friend f join fetch f.member m where f.friendMemberId=:memberId and f.friendStatus=:status", Friend.class)
                .setParameter("memberId", memberId)
                .setParameter("status", FriendStatus.WAIT)
                .getResultList();
    }

    public void removes(Long memberId, Long friendId) {
        List<Friend> removeList = em.createQuery("select f from Friend f join fetch f.member m " +
                        "where (m.id=:memberId and f.friendMemberId=:friendId) or (m.id=:friendId and f.friendMemberId=:memberId)", Friend.class)
                .setParameter("memberId", memberId)
                .setParameter("friendId", friendId)
                .getResultList();

        if (removeList.size() < 2) {
            throw new RuntimeException();
        }

        removeList.stream()
                .forEach(friend -> em.remove(friend));
    }
}
