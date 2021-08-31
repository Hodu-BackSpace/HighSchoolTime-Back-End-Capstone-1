package hodubackspace.highschooltime.api.service.dto.response;

import hodubackspace.highschooltime.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMemberInfo {
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String highSchoolName;
    private int grade;
    private int classNum;

    public static ResponseMemberInfo of(Member member) {
        ResponseMemberInfo responseMemberInfo =
                new ResponseMemberInfo(member.getEmail(), member.getPassword(), member.getName(),
                        member.getNickName(),member.getSchoolInfo().getHighSchoolName(),member.getSchoolInfo().getGrade(),member.getSchoolInfo().getClassNum());
        return responseMemberInfo;
    }

}
