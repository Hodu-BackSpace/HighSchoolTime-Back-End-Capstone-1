package hodubackspace.highschooltime.domain;

import hodubackspace.highschooltime.api.controller.dto.request.RequestModifyMemberDto;
import hodubackspace.highschooltime.domain.common.DateLog;
import hodubackspace.highschooltime.domain.embeddable.SchoolInfo;
import lombok.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"messages","boards","friends"})
public class Member extends DateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;

    private String name;
    private String nickName;

    @Embedded
    private SchoolInfo schoolInfo;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "fromMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Friend> friends = new ArrayList<>();

    @Builder(builderMethodName = "createMemberEntity")
    public Member(String email, String password, String name, String nickName, SchoolInfo schoolInfo,Authority authority) {
        Assert.hasText(email, "email is NULL");
        Assert.hasText(password, "password is NULL");
        Assert.hasText(name, "name is NULL");
        Assert.hasText(nickName, "nickName is NULL");
        Assert.notNull(schoolInfo, "schoolInfo is NULL");

        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.schoolInfo = schoolInfo;
        this.authority = authority;
        setCreatedDate(LocalDateTime.now());
        setLastModifiedDate(LocalDateTime.now());
    }

    public void updatePasswordNickNameOptional(RequestModifyMemberDto memberDto) {
        if (StringUtils.hasText(memberDto.getPassword())) {
            this.password = memberDto.getPassword();
        }

        if (StringUtils.hasText(memberDto.getNickName())) {
            this.nickName = memberDto.getNickName();
        }
    }

    public void removeFriend(Friend friend) {
        this.getFriends().remove(friend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Member member = (Member) o;
        return Objects.equals(getId(), member.getId()) && Objects.equals(getEmail(), member.getEmail()) && Objects.equals(getPassword(), member.getPassword()) && Objects.equals(getName(), member.getName()) && Objects.equals(getNickName(), member.getNickName()) && Objects.equals(getSchoolInfo(), member.getSchoolInfo()) && getAuthority() == member.getAuthority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getEmail(), getPassword(), getName(), getNickName(), getSchoolInfo(), getAuthority());
    }
}
