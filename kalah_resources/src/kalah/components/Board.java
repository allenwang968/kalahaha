package kalah.components;

import kalah.util.MoveEndState;

public class Board {

    private Player[] _players;

    public Board(Player[] players) {
        _players = players;
    }

    public boolean isPlayerEmpty(int playerId) {
        House[] houses = _players[playerId].getHouses();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i].getSeeds() > 0) {
                return false;
            }
        }

        return true;
    }

    public int getPlayerScore(int playerId) {
        return _players[playerId].getStore().getSeeds();
    }

    public int performMoveOnHouse(int playerId, int houseNo) {
        return _players[playerId].clearHouse(houseNo);
    }

    public MoveEndState sowSeeds(int playerId, int startingHouse, int noOfSeeds, boolean sowIntoStore) {
        return _players[playerId].sowSeeds(startingHouse, noOfSeeds, sowIntoStore);
    }

    public Player getPlayer(int playerId) {
        return  _players[playerId];
    }

}
