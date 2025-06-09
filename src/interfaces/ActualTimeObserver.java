package interfaces;

public interface ActualTimeObserver {
    void updateOnTimeChange(String actualTime);

    void reset();
}
