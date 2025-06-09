package timer;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeRegister {
    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final Instant startInstant;
    private final Instant endInstant;

    public TimeRegister(Instant startInstant, Instant endInstant) {
        this.startInstant = startInstant;
        this.endInstant = endInstant;
    }

    public String getStartTime() {
        return formatTime(startInstant);
    }

    public String getEndTime() {
        return formatTime(endInstant);
    }

    public String getElapsedTime() {
        long elapsedMillis = Duration.between(startInstant, endInstant).toMillis();

        long minutes = elapsedMillis / 60_000;
        long seconds = (elapsedMillis % 60_000) / 1_000;
        long milliseconds = elapsedMillis % 1_000;

        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);
    }

    private String formatTime(Instant instant) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, DEFAULT_ZONE);
        return TIME_FORMATTER.format(dateTime);
    }
}
