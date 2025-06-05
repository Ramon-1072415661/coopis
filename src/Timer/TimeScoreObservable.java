package Board.Timer;

public interface TimeScoreObservable {
    void addTimeObservers(TimeScoreObservers observers);
    void notifyObservers(TimeRegister register,int score);
}
