package hodubackspace.highschooltime.domain.embeddable;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SchoolInfo {
    private String highSchoolName;
    private int grade;
    private int classNum;
}
