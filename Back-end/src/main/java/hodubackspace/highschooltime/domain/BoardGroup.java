package hodubackspace.highschooltime.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_group_id")
    private Long id;

    private String boardName;

    @OneToMany(mappedBy = "boardGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @Builder(builderMethodName = "createBoardGroupEntity")
    public BoardGroup(String boardName) {
        this.boardName = boardName;
    }
}
