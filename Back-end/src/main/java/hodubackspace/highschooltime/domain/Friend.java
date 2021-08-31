package hodubackspace.highschooltime.domain;

import hodubackspace.highschooltime.domain.common.DateLog;
import hodubackspace.highschooltime.domain.status.FriendStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends DateLog{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    private Long friendMemberId;
    private String friendMemberName;

    @Enumerated(value = EnumType.STRING)
    private FriendStatus friendStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "friend_member_fk"))
    private Member member;

    @Builder(builderMethodName = "createFriendEntity")
    public Friend(Long friendMemberId, String friendMemberName, FriendStatus friendStatus, Member member) {
        Assert.notNull(member,"member is NULL");

        this.friendMemberId = friendMemberId;
        this.friendMemberName = friendMemberName;
        this.friendStatus = friendStatus;
        this.member = member;
    }

    public void updateStatus(FriendStatus friendStatus) {
        this.friendStatus = friendStatus;
    }
}
