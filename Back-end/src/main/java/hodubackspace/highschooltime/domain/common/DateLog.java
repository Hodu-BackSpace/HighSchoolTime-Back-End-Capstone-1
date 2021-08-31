package hodubackspace.highschooltime.domain.common;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class DateLog {
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
