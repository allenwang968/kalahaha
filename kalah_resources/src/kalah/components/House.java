package kalah.components;

public class House extends Pit {

    public House(int seeds) {
        super(seeds);
    }

    public int clearSeeds() {
        int seeds = _seeds;
        _seeds = 0;

        return seeds;
    }
}
