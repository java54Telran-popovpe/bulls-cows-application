package telran.bullsCows;

import telran.view.Item;
import telran.view.Menu;
import telran.view.SystemInputOutput;

public class BullsCowsApp {

	public static void main(String[] args) {
		BullsCowsService bullsCowsService = new BullsCowsMapImpl();
		Item[] mainMenuItems  = BullsCowsApplItems.getGameItems(bullsCowsService).toArray(Item[]::new);
		Menu menu = new Menu("Please choose action", mainMenuItems);
		menu.perform(new SystemInputOutput());
	}

}
