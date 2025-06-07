package Timer;

public interface ActualTimeObservable {
    void addTimeMacthObserver(ActualTimeObserver observer);

    void notifyActualTime();
}