package hodubackspace.highschooltime.domain;

import hodubackspace.highschooltime.domain.common.DateLog;
import hodubackspace.highschooltime.domain.status.FriendStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
        addFriendList(member);
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
    }

    public void updateStatus(FriendStatus friendStatus) {
        this.friendStatus = friendStatus;
    }

    public void addFriendList(Member member) {
        this.member = member;
        member.getFriends().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Friend friend = (Friend) o;
        return Objects.equals(getId(), friend.getId()) && Objects.equals(getFriendMemberId(), friend.getFriendMemberId()) && Objects.equals(getFriendMemberName(), friend.getFriendMemberName()) && getFriendStatus() == friend.getFriendStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getFriendMemberId(), getFriendMemberName(), getFriendStatus());
    }
}
