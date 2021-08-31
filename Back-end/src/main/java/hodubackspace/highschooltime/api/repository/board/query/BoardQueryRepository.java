package hodubackspace.highschooltime.api.repository.board.query;

import hodubackspace.highschooltime.api.repository.dto.ResponseBoardList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {

    private final EntityManager em;

    public List<ResponseBoardList> findAllByBoardNameWithRegion(String boardName, String whereWrite, int page) {
        return em.createQuery("select new hodubackspace.highschooltime.api.repository.dto.ResponseBoardList(b.id,b.title,b.content,b.likeCount) from Board b " +
                        "join b.boardGroup bg " +
                        "on (bg.boardName=:boardName and b.whereWrite = :whereWrite)", ResponseBoardList.class)
                .setParameter("boardName", boardName)
                .setParameter("whereWrite", whereWrite)
                .setFirstResult((page-1) * 10)
                .setMaxResults(10)
                .getResultList();
    }

}
