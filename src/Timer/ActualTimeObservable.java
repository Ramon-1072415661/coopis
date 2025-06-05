package Timer;

import java.time.Instant;
import java.util.ArrayList;

public interface ActualTimeObservable {
    void addTimeMacthObserver(ActualTimeObserver observer);
    void notifyActualTime();
    }

