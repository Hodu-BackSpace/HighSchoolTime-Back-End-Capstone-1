package hodubackspace.highschooltime.api.repository.message.query;

import hodubackspace.highschooltime.api.advice.exception.found.NotFoundException;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseMessageDto;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseReceivedMessageDto;
import hodubackspace.highschooltime.api.repository.message.query.dto.ResponseSentMessageDto;
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
public class MessageQueryRepository {

    private final EntityManager em;

    public Optional<ResponseMessageDto> findOneReceiveMessage(Long messageId) {
        try {
            return Optional.ofNullable(em.createQuery("select new hodubackspace.highschooltime.api.repository.message.query.dto.ResponseMessageDto(me.id,me.fromMember.id,me.fromMember.name,me.content,me.createdDate) " +
                            "from Message me join me.fromMember " +
                            "where me.id=:id", ResponseMessageDto.class)
                    .setParameter("id", messageId)
                    .getSingleResult());
        } catch (NoResultException e) {
            throw new NotFoundException();
        }
    }

    public List<ResponseReceivedMessageDto> findAllReceivedMessage(Long memberId, int page) {
        return em.createQuery("select new hodubackspace.highschooltime.api.repository.message.query.dto.ResponseReceivedMessageDto(me.id,fm.id,me.content,me.createdDate) " +
                        "from Message me " +
                        "join me.fromMember fm " +
                        "where me.toMemberId=:id ", ResponseReceivedMessageDto.class)
                .setParameter("id", memberId)
                .setFirstResult((page-1) * 5)
                .setMaxResults(5)
                .getResultList();
    }

    public List<ResponseSentMessageDto> findAllSentMessage(Long memberId, int page) {
        return em.createQuery("select new hodubackspace.highschooltime.api.repository.message.query.dto.ResponseSentMessageDto(me.id,me.toMemberId,me.content,me.createdDate) " +
                        "from Message me " +
                        "join me.fromMember fm on fm.id=:id ", ResponseSentMessageDto.class)
                .setParameter("id", memberId)
                .setFirstResult((page-1) * 5)
                .setMaxResults(5)
                .getResultList();
    }

}
