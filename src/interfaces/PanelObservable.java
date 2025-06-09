package interfaces;

public interface PanelObservable {
    void addObserver(PanelObserver observer);

    void notifyObservers();
}
