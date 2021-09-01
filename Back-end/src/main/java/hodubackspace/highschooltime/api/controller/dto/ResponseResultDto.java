package hodubackspace.highschooltime.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseResultDto<T> {
    int count;
    T data;
}
