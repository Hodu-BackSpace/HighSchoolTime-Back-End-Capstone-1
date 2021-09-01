package hodubackspace.highschooltime.init.init;

import hodubackspace.highschooltime.domain.Board;
import hodubackspace.highschooltime.domain.BoardGroup;
import hodubackspace.highschooltime.domain.Comment;
import hodubackspace.highschooltime.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDb {

    private final InitBoard initBoard;

    @PostConstruct
    public void init() {
//        initBoard.dbInit();
//        initBoard.dbInit2();
    }


    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitBoard {
        private final EntityManager em;
        private final static List<String> boardNameList = new ArrayList<>(List.of("freeBoard", "hotBoard", "matchBoard", "infoBoard"));

        public void dbInit() {
            boardNameList.forEach(b -> em.persist(BoardGroup.createBoardGroupEntity().boardName(b).build()));
            log.info("Init Board Group Success");
        }

        public void dbInit2() {
            Member findMember = em.find(Member.class, 1L);
            BoardGroup boardGroup = em.find(BoardGroup.class, 1L);

            // create Board
            Board newBoard = Board.createBoardEntity()
                    .title("title")
                    .content("content")
                    .boardGroup(boardGroup)
                    .member(findMember)
                    .build();

            em.persist(newBoard);

            log.info("Init Create Board Success");

            // create Comments
            for (int i = 0; i < 5; i++) {
                em.persist(Comment.createCommentEntity()
                        .board(newBoard)
                        .member(findMember)
                        .content("comment" + i)
                        .build());
                log.info("[{}] Init Create Comments Success",i);
            }

        }
    }
}
