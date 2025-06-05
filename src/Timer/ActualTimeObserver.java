package Timer;

public interface ActualTimeObserver {
    void updateOnTimeChange(String actualTime);
    void reset();
}
