package kalah.util;

public class MoveEndState {

    private boolean _playerStore;
    private int _remainingSeeds;
    private int _lastHouseOwner;
    private int _lastHouseNumber;

    public MoveEndState(boolean playerStore, int remainingSeeds, int lastHouseOwner, int lastHouseNumber) {
        _playerStore = playerStore;
        _remainingSeeds = remainingSeeds;
        _lastHouseOwner = lastHouseOwner;
        _lastHouseNumber = lastHouseNumber;
    }

    public boolean getPlayerStore() {
        return _playerStore;
    }

    public int getRemainingSeeds() {
        return _remainingSeeds;
    }

    public int getLastHouseOwner() {
        return _lastHouseOwner;
    }

    public int getLastHouseNumber() {
        return _lastHouseNumber;
    }
}
