package hodubackspace.highschooltime.api.controller.dto.request;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class RequestLoginMemberDto implements Serializable {

    @Email
    private String email;

    @Size(min = 8,max = 16)
    private String password;

    public RequestLoginMemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    @Override
    public String toString() {
        return "{" +
                "\"email\":\"" + email + '\"' +
                ", \"password\":\"" + password + '\"' +
                '}';
    }
}
