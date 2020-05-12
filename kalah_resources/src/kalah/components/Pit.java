package kalah.components;

public abstract class Pit {

    protected int _seeds;

    public Pit(int seeds) {
        _seeds = seeds;
    }

    public int getSeeds() {
        return _seeds;
    }

    public void sowSeed() {
        _seeds++;
    }
}
