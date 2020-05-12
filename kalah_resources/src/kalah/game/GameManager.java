package kalah.game;

import kalah.components.*;

public class GameManager implements IGameManager {

    private final Board _board;
    private final int _noOfPlayers;
    private int _currentTurn;

    public GameManager(Board board, int noOfPlayers) {
        _board = board;
        _noOfPlayers = noOfPlayers;
        _currentTurn = 0;
    }

    @Override
    public int initialise() {
        _currentTurn = 1;
        return _currentTurn;
    }

    @Override
    public int nextTurn() {
        if (++_currentTurn > _noOfPlayers) {
            _currentTurn = 1;
            return 1;
        }

        return _currentTurn;
    }

    @Override
    public int[][] getBoardState() {

        int[][] boardState = new int[_noOfPlayers][];
        for (int i = 0; i < _noOfPlayers; i++) {
            Pit[] pits = _board.getPlayer(i).getPits();
            int[] playerState = new int[pits.length];
            for (int j = 0; j < pits.length; j++) {
                playerState[j] = pits[j].getSeeds();
            }
            boardState[i] = playerState;
        }

        return boardState;
    }

    @Override
    public boolean isGameOver() {
        if (_currentTurn != 0 && _board.isPlayerEmpty(_currentTurn - 1)) {
            return true;
        }

        return false;
    }
}
