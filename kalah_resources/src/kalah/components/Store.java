package kalah.components;

public class Store extends Pit {

    public Store(int startingSeeds) {
        super(startingSeeds);
    }

    public void capture(int seeds) {
        _seeds += seeds;
    }
}
