package hodubackspace.highschooltime.api.common.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTokenDto {

    String grantType;
    String accessToken;
    Date accessTokenExpiresIn;
    String refreshToken;


}
