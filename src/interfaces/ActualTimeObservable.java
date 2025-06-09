package interfaces;

public interface ActualTimeObservable {
    void addTimeMatchObserver(ActualTimeObserver observer);

    void notifyActualTime();
}