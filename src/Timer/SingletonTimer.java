package Timer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SingletonTimer implements TimeScoreObservable, ActualTimeObservable {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static SingletonTimer instance = null;
    private static  Optional<Instant> startInstant = Optional.empty();
    private static ArrayList<TimeScoreObservers> observers = new ArrayList<>();
    private static ArrayList<ActualTimeObserver> actualTimeObserver = new ArrayList<>();

    public static SingletonTimer getInstance(){
        if (instance == null){
            instance = new SingletonTimer();
        }
        return instance;
    }
    private SingletonTimer(){}

    public void start(){
        startInstant = Optional.of(Instant.now());
        startMatchTime();
    }
    public void stopPlacardRegister(int score){
        if(startInstant.isEmpty()) return;
        stopMachTime();
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

    private void startMatchTime(){
        scheduler.scheduleAtFixedRate(this::notifyActualTime,0,1, TimeUnit.MILLISECONDS);
    }
    public void stopMachTime(){
        scheduler.shutdown();
        for( ActualTimeObserver observer : actualTimeObserver){
            observer.reset();
        }
    }
    @Override
    public void addTimeMacthObserver(ActualTimeObserver observer) {
        actualTimeObserver.add(observer);
    }

    @Override
    public void notifyActualTime() {
        if (startInstant.isEmpty()) return;
        for(ActualTimeObserver observer : actualTimeObserver){
                observer.updateOnTimeChange(new TimeRegister(startInstant.get(),Instant.now()).beetweenTime());
        }
    }



}
