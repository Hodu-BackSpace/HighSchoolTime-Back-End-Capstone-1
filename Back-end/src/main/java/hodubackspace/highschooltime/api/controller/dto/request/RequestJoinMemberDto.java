package hodubackspace.highschooltime.api.controller.dto.request;

import hodubackspace.highschooltime.domain.Authority;
import hodubackspace.highschooltime.domain.Member;
import hodubackspace.highschooltime.domain.embeddable.SchoolInfo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
public class RequestJoinMemberDto {

    @Email
    private String email;

    @NotBlank
    @Size(min = 8,max = 16)
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String nickName;

    @NotBlank
    private String highSchoolName;

    @Range(min = 1,max = 3)
    private int grade;

    @Range(min = 1,max = 20)
    private int classNum;

    public static Member toMemberEntity(RequestJoinMemberDto requestJoinMemberDto) {
        return Member.createMemberEntity()
                .email(requestJoinMemberDto.getEmail())
                .password(requestJoinMemberDto.getPassword())
                .name(requestJoinMemberDto.getName())
                .nickName(requestJoinMemberDto.getNickName())
                .schoolInfo(new SchoolInfo(requestJoinMemberDto.getHighSchoolName(), requestJoinMemberDto.getGrade(), requestJoinMemberDto.getClassNum()))
                .authority(Authority.ROLE_USER)
                .build();

    }

    public void encodePassword(String password) {
        this.password = password;
    }

}
