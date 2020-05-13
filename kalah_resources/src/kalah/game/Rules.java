package kalah.game;

import kalah.components.Board;
import kalah.components.House;
import kalah.components.Pit;
import kalah.components.Store;
import kalah.util.MoveEndState;

public class Rules implements IRules {

    private final Board _board;
    private final int _noOfPlayers;
    private final int _noOfHouses;

    public Rules(Board board, int noOfPlayers, int noOfHouses) {
        _board = board;
        _noOfPlayers = noOfPlayers;
        _noOfHouses = noOfHouses;
    }

    @Override
    public MoveEndState move(int houseNo, int player) {
        houseNo--;
        player--;
        boolean sowIntoStore = true;
        int currentPlayer = player;
        int seeds = _board.performMoveOnHouse(currentPlayer, houseNo);
        MoveEndState endState = _board.sowSeeds(currentPlayer, houseNo + 1, seeds, sowIntoStore);
        while (endState.getRemainingSeeds() > 0) {
            if (++currentPlayer == _noOfPlayers) {
                currentPlayer = 0;
            }
            if (currentPlayer == player) {
                sowIntoStore = true;
            } else {
                sowIntoStore = false;
            }
            endState = _board.sowSeeds(currentPlayer, 0, endState.getRemainingSeeds(), sowIntoStore);
        }

        return endState;
    }

    @Override
    public boolean illegalMove(int houseNo, int currentTurn) {
        return _board.getPlayer(currentTurn - 1).getPits()[houseNo - 1].getSeeds() == 0;
    }

    @Override
    public boolean extraTurn(MoveEndState endState) {
        if (endState.getPlayerStore()) {
            return true;
        }

        return false;
    }

    @Override
    public void capture(MoveEndState endState, int currentTurn) {
        if (endState.getLastHouseNumber() != _noOfHouses + 1) {
            if (endState.getLastHouseOwner() == currentTurn) {
                int oppositeHouseNumber = _noOfHouses - endState.getLastHouseNumber() + 1;
                Pit lastHouse = _board.getPlayer(endState.getLastHouseOwner() - 1).getPits()[endState.getLastHouseNumber() - 1];
                Pit oppositeHouse = _board.getPlayer(_noOfPlayers - endState.getLastHouseOwner()).getPits()[oppositeHouseNumber - 1];
                if (lastHouse.getSeeds() == 1 && oppositeHouse.getSeeds() > 0) {
                    int lastHouseSeeds = ((House)lastHouse).clearSeeds();
                    int oppositeHouseSeeds = ((House)oppositeHouse).clearSeeds();
                    ((Store)_board.getPlayer(currentTurn - 1).getPits()[_noOfHouses]).capture(lastHouseSeeds + oppositeHouseSeeds);
                }
            }
        }
    }

    @Override
    public int getWinner() {

        int maxSeeds = _board.getPlayerScore(0);
        int winner = 0;
        for (int i = 1; i < _noOfPlayers; i++) {
            if (_board.getPlayerScore(i) == maxSeeds) {
                return 0;
            } else {
                winner = i;
            }
        }

        return winner + 1;
    }
}
