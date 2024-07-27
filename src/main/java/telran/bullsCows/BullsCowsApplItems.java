package telran.bullsCows;

import java.util.Arrays;
import java.util.List;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class BullsCowsApplItems {
	
	static BullsCowsService bullsCowsService;
	static long currentGameId;
	
	public static List<Item> getGameItems(BullsCowsService bullsCowsService) {
		BullsCowsApplItems.bullsCowsService = bullsCowsService;
		Item[] items = {Item.of("Start new game", BullsCowsApplItems::startNewGame), Item.ofExit() };
		return Arrays.stream(items).toList();
	}
	
	private static void doSuggest( InputOutput io ) {
		String clientSeq = io.readString("Enter four unique digits");
		List<MoveResult> results = bullsCowsService.getResults(currentGameId, new Move(currentGameId,clientSeq));
		printPesult(results, io);
	}
	private static void printPesult(List<MoveResult> results, InputOutput io) {
		if ( bullsCowsService.isGameOver(currentGameId)) {
			io.writeLine("Game is over");
		} else {
			results.forEach( r -> io.writeLine(String.format("\"%s\"\tbulls: %d, cows: %d", r.clientSequence(), r.bullsN(), r.cowsN())));
		}
		
	}

	private static void startNewGame( InputOutput io ) {
		currentGameId = bullsCowsService.createNewGame();
		Item[] startMenuItems = { Item.of("Enter 4 non-repeatable digit [0-9]", BullsCowsApplItems::doSuggest), Item.ofExit() };
		Menu currentGameMenu = new Menu("Please choose", startMenuItems );
		currentGameMenu.perform(io);
	}
	
}
