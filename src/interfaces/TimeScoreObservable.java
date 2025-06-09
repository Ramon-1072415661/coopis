package interfaces;

import timer.TimeRegister;

public interface TimeScoreObservable {
    void addTimeObservers(TimeScoreObservers observers);

    void notifyObservers(TimeRegister register, int score);
}
