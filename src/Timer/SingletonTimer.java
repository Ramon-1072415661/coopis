package Board.Timer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

public class SingletonTimer implements TimeScoreObservable {
    private static SingletonTimer instance = null;
    private static  Optional<Instant> startInstant = Optional.empty();
    private static ArrayList<TimeScoreObservers> observers = new ArrayList<>();

    public static SingletonTimer getInstance(){
        if (instance == null){
            instance = new SingletonTimer();
        }
        return instance;
    }

    public void start(){
        startInstant = Optional.of(Instant.now());
    }
    public void stopMatchRegister(int score){
        if(startInstant.isEmpty()) return;
        TimeRegister register = new TimeRegister(startInstant.get(),Instant.now());
        notifyObservers(register,score);
    }

    @Override
    public void addTimeObservers(TimeScoreObservers observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(TimeRegister register, int score) {
        for(TimeScoreObservers observer : observers){
            observer.timeUpdate(register,score);
        }
    }
}
