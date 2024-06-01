package co.bontech.exam.rest.vm;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
@Data
public class ServiceVM {
    @NotNull
    Long serviceId;
    @NotNull
    LocalDateTime startTime;
    @NotNull
    LocalDateTime endTime;

    @AssertTrue(message = "Duration between start and end is more than 12 hours")
    public boolean isDurationCorrect() {
        Duration duration = Duration.between(startTime, endTime);
        long hours = Math.abs(duration.toHours());
        return hours <= 12;
    }
    @AssertTrue(message = "satrt and end should be in same day")
    public boolean isSameDay() {

        return startTime.toLocalDate().equals(endTime.toLocalDate());
    }

}
