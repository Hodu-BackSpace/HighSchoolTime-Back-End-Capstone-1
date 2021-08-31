package hodubackspace.highschooltime.domain;

import io.jsonwebtoken.lang.Assert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",foreignKey = @ForeignKey(name = "comment_member_fk"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", foreignKey = @ForeignKey(name = "comment_board_fk"))
    private Board board;

    @Builder(builderMethodName = "createCommentEntity")
    public Comment(String content, Member member, Board board) {
        Assert.hasText(content, "Content must not NULL");

        this.content = content;
        this.member = member;
        updateBoard(board);
    }

    private void updateBoard(Board board){
        this.board = board;
        board.addComment(this);
    }

}
