package telran.bulls.cows.net;

import java.util.ArrayList;
import java.util.List;
import telran.bulls.net.BullsCowsService;

import telran.bulls.net.Move;
import telran.bulls.net.MoveResult;
import telran.view.Item;

public class BullsCowsApplItems {

	public static List<Item> getItems(BullsCowsService bullsCowsService) {
		List<Item> items = new ArrayList<>();
	    final int maxAttempts = 1;

	    items.add(Item.of("Start new game", io -> {
	        long gameId = bullsCowsService.createNewGame();
	        io.writeLine("New game started with ID: " + gameId);
	        int attempts = 0;

	        while (!bullsCowsService.isGameOver(gameId)) {
	            if (attempts >= maxAttempts) {
	                io.writeLine("You have exceeded the maximum number of attempts. You lost!");
	                break;
	            }
	            String userInput = io.readStringPredicate(
	                "Enter 4 unique digits:",
	                "Invalid input. Please enter exactly 4 unique digits.",
	                input -> input.matches("\\d{4}"));

	            Move move = new Move(Long.toString(gameId), userInput);
	            List<MoveResult> results = bullsCowsService.getResults(gameId, move);
	            attempts++;
	            if (results != null && !results.isEmpty()) {
	                io.writeLine(results.get(results.size() - 1).toString());
	            } else {
	                io.writeLine("You have exceeded the maximum number of attempts. You lost!");
	                break;
	            }
	        }

	        if (bullsCowsService.isGameOver(gameId)) {
	            io.writeLine("Game over");
	        }
	    }));

	    return items;
	}
}