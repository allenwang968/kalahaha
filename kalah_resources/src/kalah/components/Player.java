package kalah.components;

import kalah.util.MoveEndState;

public class Player {

    private int _id;
    private Pit[] _pits;
    private static int players = 0;

    public Player(Store store, House[] houses) {
        players++;
        _id = players;
        _pits = new Pit[houses.length + 1];
        for (int i = 0; i < houses.length; i++) {
            _pits[i] = houses[i];
        }
        _pits[_pits.length - 1] = store;
    }

    public Store getStore() {
        return (Store)_pits[_pits.length - 1];
    }

    public House[] getHouses() {
        House[] houses = new House[_pits.length - 1];
        for (int i = 0; i < houses.length; i++) {
            houses[i] = (House)_pits[i];
        }
        return houses;
    }

    public Pit[] getPits() {
        return _pits;
    }

    public int clearHouse(int houseNo) {
        return ((House)_pits[houseNo]).clearSeeds();
    }

    public MoveEndState sowSeeds(int startingHouse, int noOfSeeds, boolean sowIntoStore) {
        int maxSows = _pits.length - startingHouse;
        if (!sowIntoStore) {
            maxSows--;
        }
        int seedsToBeSowed = noOfSeeds;
        int remainingSeedsToBeSowed = 0;
        if (noOfSeeds > maxSows) {
            seedsToBeSowed = maxSows;
            remainingSeedsToBeSowed = noOfSeeds - maxSows;
        }
        for (int i = startingHouse; i < seedsToBeSowed + startingHouse; i++) {
            _pits[i].sowSeed();
        }
        MoveEndState endState = new MoveEndState(seedsToBeSowed + startingHouse == _pits.length, remainingSeedsToBeSowed, _id, seedsToBeSowed + startingHouse);

        return endState;
    }
}
