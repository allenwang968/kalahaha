package kalah.game;

public interface IGameManager {

    public int initialise();
    public int nextTurn();
    public int[][] getBoardState();
    public boolean isGameOver();
}
