package hodubackspace.highschooltime.api.advice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto {

    private String fieldName;
    private String message;
}
