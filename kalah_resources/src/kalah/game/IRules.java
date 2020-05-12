package kalah.game;

import kalah.util.MoveEndState;

public interface IRules {

    public MoveEndState move(int houseNo, int currentTurn);
    public boolean illegalMove(int houseNo, int currentTurn);
    public boolean extraTurn(MoveEndState endState);
    public void capture(MoveEndState endState, int currentTurn);
    public int getWinner();
}
