package kalah.util;

import com.qualitascorpus.testsupport.IO;

public class IOManager implements IIOManager {

    private final IO _io;
    private final int _noOfHouses;
    private final String PROMPT = "Player P%d's turn - Specify house number or 'q' to quit: ";
    private final String QUIT_MSG = "q";
    private final int QUIT_KEY = -1;

    public IOManager(IO io, int noOfHouses) {
        _io = io;
        _noOfHouses = noOfHouses;
    }

    @Override
    public int readInput(int playerId) {
        return _io.readInteger(String.format(PROMPT, playerId), 1, _noOfHouses, QUIT_KEY, QUIT_MSG);
    }

    @Override
    public void printBoard(int[][] boardState) {
        outerLines();
        for (int i = boardState.length - 1; i >= 0; i--) {
            String pits = "| ";
            if (i % 2 == 0) {
                if (boardState[1 - i][boardState[i].length - 1] <= 9) {
                    pits += " ";
                }
                pits += Integer.toString(boardState[1 - i][boardState[i].length - 1]);
                for (int j = 0; j < boardState[i].length - 1; j++) {
                    pits += " |";
                    if (j + 1 <= 9) {
                        pits += " ";
                    }
                    pits += (j + 1) + "[";
                    if (boardState[i][j] <= 9) {
                        pits += " ";
                    }
                    pits += boardState[i][j] + "]";
                }
                pits += " | P" + (i + 1) + " |";
            } else {
                pits += "P" + (i + 1);
                for (int j = boardState[i].length - 2; j >= 0; j--) {
                    pits += " |";
                    if (j + 1 <= 9) {
                        pits += " ";
                    }
                    pits += (j + 1) + "[";
                    if (boardState[i][j] <= 9) {
                        pits += " ";
                    }
                    pits += boardState[i][j] + "]";
                }
                pits += " | ";
                if (boardState[1 - i][boardState[i].length - 1] <= 9) {
                    pits += " ";
                }
                pits += boardState[1 - i][boardState[i].length - 1] + " |";
            }

            _io.println(pits);
            if (i != 0) {
                innerLines();
            }
        }
        outerLines();
    }

    @Override
    public void outerLines() {
        _io.println("+----+-------+-------+-------+-------+-------+-------+----+");
    }

    @Override
    public void innerLines() {
        _io.println("|    |-------+-------+-------+-------+-------+-------|    |");
    }

    @Override
    public void gameOver() {
        _io.println("Game over");
    }

    @Override
    public void illegalMove() {
        _io.println("House is empty. Move again.");
    }

    @Override
    public void gameResults(int winner) {
        if (winner == 0) {
            _io.println("A tie!");
        } else {
            _io.println("Player " + winner + " wins!");
        }
    }
}
