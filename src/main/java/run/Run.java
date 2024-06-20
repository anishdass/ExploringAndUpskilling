package run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
    Integer id,
    @NotEmpty
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    @Positive
    Integer miles,
    Location location
){
    public Run {
        if (completedOn.isBefore(startedOn)) {
            throw new IllegalArgumentException("Completed on must be after started on");
        }
    }

}