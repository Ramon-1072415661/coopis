package managers;

import interfaces.ActualTimeObservable;
import interfaces.ActualTimeObserver;
import interfaces.TimeScoreObservable;
import interfaces.TimeScoreObservers;
import timer.TimeRegister;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class TimerManager implements TimeScoreObservable, ActualTimeObservable {
    private final List<TimeScoreObservers> timeObservers = new CopyOnWriteArrayList<>();
    private final List<ActualTimeObserver> actualTimeObservers = new CopyOnWriteArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Optional<Instant> startInstant = Optional.empty();
    private ScheduledFuture<?> updateTask;

    private static volatile TimerManager instance;

    private TimerManager() {
    }

    public static TimerManager getInstance() {
        if (instance == null) {
            synchronized (TimerManager.class) {
                if (instance == null) {
                    instance = new TimerManager();
                }
            }
        }
        return instance;
    }

    public void start() {
        startInstant = Optional.of(Instant.now());
        startMatchTimer();
    }

    public void stopAndNotifyScore(int score) {
        if (startInstant.isEmpty()) return;

        TimeRegister register = new TimeRegister(startInstant.get(), Instant.now());
        stopMatchTimer();
        notifyObservers(register, score);
    }

    @Override
    public void addTimeObservers(TimeScoreObservers observer) {
        timeObservers.add(observer);
    }

    @Override
    public void notifyObservers(TimeRegister register, int score) {
        for (TimeScoreObservers observer : timeObservers) {
            observer.timeUpdate(register, score);
        }
    }

    private void startMatchTimer() {
        if (updateTask != null && !updateTask.isDone()) return;
        updateTask = scheduler.scheduleAtFixedRate(this::notifyActualTime, 0, 1, TimeUnit.MILLISECONDS);
    }

    public void stopMatchTimer() {
        if (updateTask != null && !updateTask.isDone()) {
            updateTask.cancel(true);
        }
        startInstant = Optional.empty();

        for (ActualTimeObserver observer : actualTimeObservers) {
            observer.reset();
        }
    }

    @Override
    public void addTimeMatchObserver(ActualTimeObserver observer) {
        actualTimeObservers.add(observer);
    }

    @Override
    public void notifyActualTime() {
        startInstant.ifPresent(start -> {
            String elapsed = new TimeRegister(start, Instant.now()).getElapsedTime();
            for (ActualTimeObserver observer : actualTimeObservers) {
                observer.updateOnTimeChange(elapsed);
            }
        });
    }
}
