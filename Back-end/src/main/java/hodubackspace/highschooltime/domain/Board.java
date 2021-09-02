package hodubackspace.highschooltime.domain;

import hodubackspace.highschooltime.domain.common.DateLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends DateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private int likeCount;

    private String whereWrite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_group_id", foreignKey = @ForeignKey(name = "board_board_group_fk"))
    private BoardGroup boardGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "board_friend_fk"))
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder(builderMethodName = "createBoardEntity")
    public Board(String title, String content, Member member, BoardGroup boardGroup) {
        Assert.notNull(member, "member is NULL");

        this.title = title;
        this.content = content;
        this.member = member;
        this.whereWrite = member.getSchoolInfo().getHighSchoolName();
        this.likeCount = 0;
        addBoardToBoardGroup(boardGroup);
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
    }

    public void addComment(Comment comment) {
        getComments().add(comment);
    }

    public void addBoardToBoardGroup(BoardGroup boardGroup) {
        this.boardGroup = boardGroup;
        boardGroup.getBoards().add(this);
    }

    public void plusLikeCount() {
        this.likeCount += 1;
    }
}
