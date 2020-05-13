package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.components.Board;
import kalah.components.House;
import kalah.components.Player;
import kalah.components.Store;
import kalah.game.GameManager;
import kalah.game.IGameManager;
import kalah.game.IRules;
import kalah.game.Rules;
import kalah.util.IIOManager;
import kalah.util.IOManager;
import kalah.util.MoveEndState;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class
Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {

		final int NO_OF_HOUSES = 6;
		final int SEEDS_PER_HOUSE = 4;
		final int NO_OF_PLAYERS = 2;
		final int STARTING_SEEDS_IN_STORE = 0;

		Player[] players = new Player[NO_OF_PLAYERS];
		for (int i = 0; i < NO_OF_PLAYERS; i++) {
			Store store = new Store(STARTING_SEEDS_IN_STORE);
			House[] houses = new House[NO_OF_HOUSES];
			for (int j = 0; j < NO_OF_HOUSES; j++) {
				houses[j] = new House(SEEDS_PER_HOUSE);
			}
			players[i] = new Player(store, houses);
		}
		Board board = new Board(players);

		IGameManager gm = new GameManager(board, NO_OF_PLAYERS);
		IRules rules = new Rules(board, NO_OF_PLAYERS, NO_OF_HOUSES);
		IIOManager ioManager = new IOManager(io, NO_OF_HOUSES);

		int[][] boardState = gm.getBoardState();
		int currentTurn = gm.initialise();
		while(!gm.isGameOver()) {
			ioManager.printBoard(boardState);
			int input = ioManager.readInput(currentTurn);
			if (input == -1) {
				ioManager.gameOver();
				boardState = gm.getBoardState();
				ioManager.printBoard(boardState);
				break;
			}
			if (rules.illegalMove(input, currentTurn)) {
				ioManager.illegalMove();
				continue;
			}
			MoveEndState endState = rules.move(input, currentTurn);
			if (!rules.extraTurn(endState)) {
				rules.capture(endState, currentTurn);
				currentTurn = gm.nextTurn();
			}
			boardState = gm.getBoardState();
		}
//		ioManager.gameResults(rules.getWinner());
	}





}
