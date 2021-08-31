package hodubackspace.highschooltime.init.init;

import hodubackspace.highschooltime.domain.BoardGroup;
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
        initBoard.dbInit();
        log.info("Init Board Group Success");
    }


    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitBoard {
        private final EntityManager em;
        private final static List<String> boardNameList = new ArrayList<>(List.of("freeBoard", "hotBoard", "matchBoard", "infoBoard"));

        public void dbInit() {
            boardNameList.forEach(b -> em.persist(BoardGroup.createBoardGroupEntity().boardName(b).build()));
        }
    }
}
