package kalah.util;

public interface IIOManager {

    public int readInput(int playerId);
    public void printBoard(int[][] boardState);
    public void outerLines();
    public void innerLines();
    public void gameOver();
    public void illegalMove();
    public void gameResults(int results);
}
