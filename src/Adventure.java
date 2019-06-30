import java.util.Scanner;

public class Adventure {

	static GameFacade game = GameFacade.getInstance();
	static String reader;
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		game.introduction();
		
		while (!(game.isGameOver())) {

				// ler entrada do jogador
			System.out.print("\nPlayer/> ");
			String reader = s.nextLine();

				// formatar para mandar pra facade
			String[] splitted = reader.split(" ");
			commands(splitted.length, splitted);

		}

	}

	public static void commands(int commandSize, String[] commandText) {

		String command = commandText[0].toLowerCase();

		// 1-sized
		if (commandSize == 1) {

			switch (command) {

			case "help":
				game.help();
				break;

			case "view":
				game.view();
				break;

			case "inventory":
				game.inventory();
				break;

			case "pickup":
				if( game.pickUp() )
					System.out.println("\tThe item is now on your backpack.");
				break;

			case "throwaxe":
				if( game.throwAxe() )
					System.out.println("\tYou ve killed the damned troll!");
				break;
				
			case "usepotion":
				if( game.usePotion() )
					System.out.println( "\tYou ve enchanted the door." );
				break;

			case "exit":
				game.exit();
				break;
				
			case "giveup":
				game.giveUp();
				break;

			default:
				System.out.println("\tInvalid command!!");
				break;
			}
		}

		// 2-sized
		if (commandSize == 2) {

			String item = commandText[1].toLowerCase();

			if (!(item.equals("axe") || item.equals("potion") || item.equals("gold")))
				if (!(item.equals("treasure") || item.equals("key")))
					command = "invalid";

			switch (command) {
			case "moveto":
				if( game.moveToItem(item) )
					System.out.println("\tYou re now interacting with the " + item + ".");
				break;

			case "drop":
				if( game.drop(item) )
					System.out.println("\tYou dropped the " + item + ".");
				break;

			default:
				System.out.println("\tInvalid command!!");
			}

		}

		// 3-sized
		if (commandSize == 3) {

			String doorID = commandText[2].toUpperCase();

			if (!commandText[1].toLowerCase().equals("door"))
				command = "invalid";

			switch (command) {

			case "moveto":
				if( game.moveToDoor(doorID) )
					System.out.println("\tYou re now facing the door " + doorID + ".");
				break;

			default:
				System.out.println("Invalid command!!");
			}
		}
	}

}