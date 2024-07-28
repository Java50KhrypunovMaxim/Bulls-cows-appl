package telran.bulls.cows.net;

import java.util.List;

import telran.view.Item;
import telran.view.Menu;
import telran.view.*;
import telran.bulls.net.*;

public class BullsCowsAppl {
	public static void main(String[] args) {
        BullsCowsService bullsCowsService = new BullsCowsMapImpl();
        List<Item> items = BullsCowsApplItems.getItems(bullsCowsService);
        Item[] itemsArray = items.toArray(new Item[0]);
        Menu menu = new Menu("Bulls and Cows Game", itemsArray);
        InputOutput io = new SystemInputOutput();
        menu.perform(io);
    }
}
