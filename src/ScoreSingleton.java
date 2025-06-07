import java.util.ArrayList;

public final class ScoreSingleton {
    private static ScoreSingleton instance;
    public int rowsCleaned;
    public int totalScoreCurrentGame;
    public ArrayList<Integer> scoreGames;

    private ScoreSingleton() {
        scoreGames = new ArrayList<>();
    }

    public static ScoreSingleton getInstance() {
        if (instance == null) {
            instance = new ScoreSingleton();
        }
        return instance;
    }

    public void addCleanedRows(int rowsCleaned) {
        this.rowsCleaned += rowsCleaned;
    }

    public int calculeScore() {
        int scorePerRowsCleaned = 100;
        totalScoreCurrentGame = scorePerRowsCleaned * rowsCleaned;
        addCurrentScoreGameToList(totalScoreCurrentGame);

        return totalScoreCurrentGame;
    }

    private void addCurrentScoreGameToList(int totalScoreCurrentGame) {
        scoreGames.add(totalScoreCurrentGame);
    }

    public void reset() {
        rowsCleaned = 0;
    }
}
