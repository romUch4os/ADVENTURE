import java.util.Collection;

public class GameFacade {
	
	private static GameFacade instance = null;
	private Labyrinth labyrinth = null;
	private Player player = null;
	private Boolean gameOver;
	
	public static GameFacade getInstance() {
		
		if( instance == null )
			instance = new GameFacade();
		
		return instance;
	}
	
	private GameFacade() {
		
		player = new Player();
		labyrinth = new Labyrinth();
		gameOver = false;
	}
	
	public void introduction() {
		
		System.out.println( "\n\nDAMNED CAVE ADVENTURE! [Version: 1.0.0] \n" );
		System.out.print("'' 'This cave is damned!' - said Gorgomoth, right before a troll opens his skull ");
		System.out.print("with an axe. Moments later, the entire party was lying down on the ground covered ");
		System.out.print("in blood, while one of the trolls ripped off Merkina`s head and starts to yell, ");
		System.out.println("furiously. ''");
		System.out.print("The tavern keeper closes the book and raise his head with an horrible expression ");
		System.out.println("in his eyes. Every-single-one at the balcony is looking at him, terrified.");
		System.out.print("He puts a smile on his face and starts laughing: '-HAHA! This was aeons ago, ");
		System.out.print("now the cave is just a myth we tell children, so they can stay out of the woods.");
		System.out.print("I bet with any of you that the only treasure hidden in there is Narth`s dignity. ");
		System.out.println("Look at him! He is drooling at my balcony! HAHAHA! '");
		System.out.println("Everybody starts to laught and yell, happily.");
		System.out.println("You hear someone screaming: '-That round is on me! HAHAHAHA! Let`s drink, fellas!'\n");
		
		System.out.print("You were sitted in a table next to balcony, very drunk, like a drunken pig. ");
		System.out.print("A thought goes through your adventurous mind. It's hard to get up, but you made it. ");
		System.out.print("You start to walk without any sense of direction, and everything you remember after ");
		System.out.println("that is a blur.\n");
		
		System.out.print("You wake up in the middle of the forest, at the entrance of a cave. On your right, you ");
		System.out.println("see a sign written: 'TROLLS AHEAD! DO NOT GET IN!'");
		System.out.print("The hangover is beating like hell, you try to put yourself together and start walk");
		System.out.println("towards the cave.\n");
		
		System.out.print("Welcome to the Damned Cave!\nYour goal is to leave the cave with all the ");
		System.out.print("treasure that is inside. There are items such: axes, potions and door keys. ");
		System.out.println("But beware: there are trolls inside the cave, and they dont like humans!");
		System.out.print("In every room there is 0-10 pieces of treasure, a 1/3 chance of having ");
		System.out.print("an axe and/or a potion, 1/6 chance of having a troll, and each door has ");
		System.out.println("10 percent chance of being locked. You gotta escape that maze. Good look!\n");
		
		System.out.println("You can type 'help' at any time in the game to see the command list.");
		
	}
	
	public boolean isGameOver() {
		
		return gameOver;
	}
	
	// sempre que o jogador muda de sala, os trolls se movem, pegam machados
	//		e atacam o jogador se ele entra na sala
	public void atualizeMap() {
		
		labyrinth.moveTrolls();
		labyrinth.equipTrolls();
		trollAttack();
	}
	
	public void help() {
		
		System.out.println( "Welcome to Damned Cave Adventure`s HELP!" );
		System.out.println("I cant help you much, because the game is already easy to play.");
		System.out.println("Here goes some commands and items, thats all I can do for you.");
		
		System.out.println("Command list:  (they arent case sensitive)");
		System.out.println(" - help \t\t$ You know what it does");
		System.out.println(" - view \t\t$ Describes the room you are");
		System.out.println(" - inventory \t\t$ Describes the items you are carrying");
		System.out.println(" - pickUp \t\t$ Pick up the item that you are interacting with");
		System.out.println(" - throwAxe \t\t$ Throw an axe in the troll");
		System.out.println(" - usePotion \t\t$ Enchants the door so trolls cant go through");
		System.out.println(" - exit \t\t$ Go through the door");
		System.out.println(" - giveUp \t\t$ Run, coward, run!");
		System.out.println(" - moveTo (item) \t$ Interacts with that item in the room");
		System.out.println(" - drop (item) \t\t$ Drops the item from your inventory");
		System.out.println(" - moveTo door (A/B/C) \t$ Goes to the door (label) in the room");
		
		System.out.println("Items list:  (they arent case sensitive)");
		System.out.println(" - Axe");
		System.out.println(" - Potion");
		System.out.println(" - Key (toDoor)");
		System.out.println(" - Gold / Treasure");
		
	}
	
	public void view() {
		
		int id = player.getPosition();
		Room room = getRoom();
		
		System.out.println( "\tYou are now in ROOM " + id + "!" );
		System.out.println( "\tThis room have " + room.getNumDoors() + " doors;" );
		
		Collection<Item> items = room.getItems();
		
		// exibe os itens na sala
		if( items.isEmpty() )
			System.out.println( "\tThere is no items in this room;" );
		else {
			System.out.println( "\tThose are the items in the room:" );
			for( Item i : items )
				System.out.println( "\t- " + i.getDescription() + ";" );
		}
		
		// exibe a quantidade de tesouro na sala
		Treasure treasure = room.getTreasure();
		if( treasure == null )
			System.out.println( "\tThere is no treasure in this room;" );
		else
			System.out.println( "\tThere are " + treasure.getAmount() + " pieces of treasure in this room;" );
		
		// avisa se tiver troll
		if( room.getTroll() != null )
			System.out.println( "\t-- THERE IS A TROLL IN THIS ROOM! --" );
		
		// exibe com q item/porta o jogador esta interagindo
		Item item = player.getInteraction();
		if( item != null )
			System.out.println( "\tYou re interacting with one " + item.getDescription() + ".");

		Door door = player.getDoor();
		if( door != null ) {
			String label = room.getDoorLabel( door );
			System.out.println( "\tYou re interacting with the door " + label + "." );
		}
		
	}
	
	public void inventory() {
		
		Collection<Item> items = player.getItems();
		
		if( items.isEmpty() )
			System.out.println( "\tYour backpack is empty." );
		
		else {
			
			System.out.println( "\tThis is your backpack:" );
			
			for( Item i : player.getItems() )
				System.out.println( "\t\t- " + i.getDescription() + ";" );
		}
		
		System.out.println( "\tYou have " + player.getTreasure() + " pieces of treasure." );
	}
	
	public boolean pickUp() {

		// seleciona o item que o jogador esta interagindo
		Room room = getRoom();	
		Item item = player.getInteraction();
		
		if( item == null ) {
			System.out.println( "\tYou arent interacting with any item." );
			return false;
		}
		
		// caso expecifico: se for tesouro, pega
		if( item instanceof Treasure ) {
			
			player.addTreasure( ((Treasure) item).getAmount() );
			room.removeTreasure();
			player.setInteraction( null );
			return true;
		}
		
		// pega o item da sala
		player.addItem(item);
		player.setInteraction( null );
		room.removeItem(item);
			
		return true;
	}
	
	public boolean throwAxe() {
		
		Room room = getRoom();
		Troll troll = room.getTroll();
		
		// verifica se tem troll na sala
		if( troll == null ) {
			System.out.println( "\tThere is no troll in this room!" );
			return false;
		}
		
		// verifica se ojogador tem machado
		Axe axe = player.getAxe();
		if( axe == null ) {
			System.out.println( "\tYou dont have an axe!" );
			return false;
		}
		
		// se o troll tiver um machado ele é droppado na sala
		if( troll.hasAxe() ) {
			
			room.addItem( new Axe() );
			troll.setAxe( null );
		}
		
		player.removeItem(axe);
		labyrinth.removeTroll(troll);
		room.removeTroll();
		
		return true;
	}
	
	// verifica se o jogador esta interagindo com uma porta e se tem pocao para encanta-la
	public boolean usePotion() {
		
		if( player.isAtDoor() ) {
			
			Potion pot = player.getPotion();
			
			if( ! ( pot == null ) ) {
				
				Door door = player.getDoor();
				door.enchant();
				player.removeItem( pot );
				
			}
			
			else {
				System.out.println("\tYou dont have any potions.");
				return false;
			}
			
		}
		
		else {
			System.out.println("\tYou arent facing the door.");
			return false;
		}
		
		return true;
	}
	
	// verifica se o jogador esta na porta e se pode atravessar para outra sala
	public boolean exit() {
		
		if( ! ( player.isAtDoor() ) ) {
			
			System.out.println( "\tYou arent facing the door!!" );
			return false;
		}
		
		if( ! ( player.throughtDoor() ) ) {
			
			return false;
		}
		
		// verifica se o jogador saiu do labirinto
		if( player.getPosition() == 0 ) {
			
			// verifica se esta com todo tesouro para sair e acabar o jogo
			if( player.getTreasure() == labyrinth.getTreasure() ) {
				
				System.out.println( "\n\t\t'''CONGRATULATIONS!! YOU WON!!'''" );
				gameOver = true;
			}
			
			else {
				
				System.out.println( "\tARE YOU NUTS?? You cant go out!!" );
				System.out.println( "\tThere is still treasure in the labyrinth!!" );
				player.throughtDoor();
				return false;
			}
		}
		
		System.out.println( "\tYou went throught the door.\n" );
		atualizeMap();
		view();
		return true;
	}
	
	public void giveUp() {
		
		System.out.println( "\n\t\tThere he goes, cowardly! Get out of here, noob!" );
		gameOver = true;
		
	}
	
	public boolean moveToItem(String type) {
		
		Room room = getRoom();
		Item item = room.getItem(type);

		// se o jogador quiser ir pro tesouro, verifica se tem troll protegendo
		if( type.equals( "gold" ) || type.equals( "treasure" ) ) {
			
			if( room.getTroll() != null ) {
				System.out.println( "\tThere is a troll in the room. You cant reach the treasure.");
				return false;
			}
			
			Treasure treasure = room.getTreasure();
			player.setInteraction( treasure );
			player.setDoor(null);
			
			return true;
		}
		
		// verifica se tem aquele item na sala
		if( item == null ) {
			System.out.println( "\tThere is no such item in the room." );
			return false;
		}
		
		player.setInteraction(item);
		player.setDoor(null);
		
		return true;
	}
	
	public boolean drop(String type) {
		
		Room room = getRoom();
		Item item = player.getItem(type);
		
		// verifica se o jogador tem tal item
		if( item == null ) {
			System.out.println( "\tYou dont have that item!" );
			return false;
		}
		
		room.addItem(item);
		player.removeItem(item);
		player.setInteraction(item);
		
		return true;
	}
	
	public boolean moveToDoor(String doorID) {
		
		Room room = getRoom();
		
		Door door = room.getDoor(doorID);
		
		if( door == null ) {
			System.out.println( "\tThere is no such door in this room!" );
			return false;
		}
		
		int id = labyrinth.getDoorID(door);
		
		if( id == 0 )
			System.out.println( "\tYou see a big 'EXIT?' painted with blood on the door." );
		else
			System.out.println( "\tYou see a big '" + id + "' painted with blood on the door." );
		
		player.setDoor(door);
		player.setInteraction(null);
		
		return true;
	}
	
	//
	
	private Room getRoom() {
		
		return labyrinth.getRoom( player.getPosition() );
	}
	
	// verifica se tem troll na sala, se ele tem machado, e se o jogador pode se defender
	private void trollAttack() {
		
		Room room = getRoom();
		Troll troll = null;
		
		if( room.hasTroll() ) {
			
			troll = room.getTroll();
			
			if( troll.hasAxe() ) {

				Item potion = player.getItem( "potion" );
				
				if( ! ( potion == null ) ) {

					System.out.println( "\tYou barely entered the room and...." );
					System.out.println( "\tWOW! The troll throwed an axe at you, but you defended yourself with a potion(?!)." ); 
					player.removeItem( potion );
				}
				
				else {

					System.out.println( "\tYou barely entered the room and...." );
					System.out.println( "\tWOW! The troll throws an axe at you starts to laught. 'HAHAHA!'" );
					System.out.println( "\tYou feel like you re a beef less next to your thigh, right where you keep your treasure bag." );
					int value = player.getTreasure();
					room.addTreasure( value );
					player.removeTreasure();
					
				}
				
				troll.setAxe( null );
				
			}
		}
	}
	
}