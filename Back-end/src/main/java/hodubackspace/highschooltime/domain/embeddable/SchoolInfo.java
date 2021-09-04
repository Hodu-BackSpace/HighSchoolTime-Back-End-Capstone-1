package hodubackspace.highschooltime.domain.embeddable;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SchoolInfo implements Serializable {
    private String highSchoolName;
    private int grade;
    private int classNum;
}
