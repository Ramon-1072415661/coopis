package Board.Timer;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

  public class TimeRegister {
    private Instant startInstant;
    private Instant finalInstant;

    public TimeRegister(Instant startInstant, Instant finalInstant) {
        this.startInstant = startInstant;
        this.finalInstant = finalInstant;
    }

    public String startTime(){
        return formatTime(startInstant);
    }
    public String finalTime(){
        return formatTime(finalInstant);
    }
    private String formatTime(Instant instante) {
        // Define o fuso horário desejado
        ZoneId zoneId = ZoneId.systemDefault(); // ou ZoneId.of("America/Sao_Paulo")

        // Converte para LocalDateTime
        LocalDateTime dateTime = LocalDateTime.ofInstant(instante, zoneId);

        // Formata como HH:MM:SS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return dateTime.format(formatter);
    }
}
