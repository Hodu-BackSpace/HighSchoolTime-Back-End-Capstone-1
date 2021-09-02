package hodubackspace.highschooltime.domain;

import hodubackspace.highschooltime.domain.common.DateLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends DateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id",foreignKey = @ForeignKey(name = "message_from_member_fk"))
    private Member fromMember;

    private Long toMemberId;

    @Builder(builderMethodName = "createMessageEntity")
    public Message(String content, Member fromMember, Long toMemberId) {
        Assert.hasText(content,"content is NULL");

        this.content = content;
        this.toMemberId = toMemberId;
        addSendMessage(fromMember);
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
    }

    public void addSendMessage(Member member) {
        this.fromMember = member;
        member.getSendMessages().add(this);
    }

}
