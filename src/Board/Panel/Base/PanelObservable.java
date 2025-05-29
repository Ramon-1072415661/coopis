package Board.Panel.Base;

public interface PanelObservable {
    void addObserver(PanelObserver observer);
    void notifyObservers();
}
