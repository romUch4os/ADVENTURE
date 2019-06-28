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
		
		System.out.println( "\n\nDAMNED CAVE ADVENTURE! [Version: 0.9.8] \n" );
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
		System.out.print("an axe and/or a potion, 20 percent chance of having a troll, and each door has ");
		System.out.println("10 percent chance of being locked. You gotta escape that maze. Good look!\n");
		
		System.out.println("You can type 'help' at any time in the game to see the command list.");
		
	}
	
	public boolean isGameOver() {
		
		return gameOver;
	}
	
	public void atualizeMap() {		// escrever atualizeMap
		
		labyrinth.equipTrolls();
		trollAttack();
	}
	
	public void help() {
		
	}
	
	public void view() {
		
		int id = player.getPosition();
		Room room = getRoom();
		
		System.out.println( "You are in ROOM " + id + "!" );
		System.out.println( "This room have " + room.getNumDoors() + " doors;" );
		
		Collection<Item> items = room.getItems();
		
		if( items.isEmpty() )
			System.out.println( "There is no items in this room;" );
		else {
			System.out.println( "Those are the items in the room:" );
			for( Item i : items )
				System.out.println( " - " + i.getDescription() + ";" );
		}

		Treasure treasure = room.getTreasure();
		if( treasure == null )
			System.out.println( "There is no treasure in this room;" );
		else
			System.out.println( "There are " + treasure.getAmount() + " pieces of treasure in this room;" );
		
		if( room.getTroll() != null )
			System.out.println( "THERE IS A TROLL IN THIS ROOM!" );
		
		Item item = player.getInteraction();
		if( item != null )
			System.out.println( "You re interacting with one " + item.getDescription() + ".");

		Door door = player.getDoor();
		if( door != null ) {
			String label = room.getDoorLabel( door );
			System.out.println( "You re interacting with the door " + label + "." );
		}
		
	}
	
	public void inventory() {
		
		Collection<Item> items = player.getItems();
		
		if( items.isEmpty() )
			System.out.println( "Your backpack is empty." );
		
		else {
			
			System.out.println( "This is your backpack:" );
			
			for( Item i : player.getItems() )
				System.out.println( " - " + i.getDescription() + ";" );
		}
		
		System.out.println( "You have " + player.getTreasure() + " pieces of treasure." );
	}
	
	public boolean pickUp() {

		Room room = getRoom();	
		Item item = player.getInteraction();
		
		if( item == null ) {
			System.out.println( "You arent interacting with any item." );
			return false;
		}
		
		if( item instanceof Treasure ) {
			
			player.addTreasure( ((Treasure) item).getAmount() );
			room.removeTreasure();
			player.setInteraction( null );
			return true;
		}
			
		player.addItem(item);
		player.setInteraction( null );
		room.removeItem(item);
			
		return true;
	}
	
	public boolean throwAxe() {
		
		Room room = getRoom();
		Troll troll = room.getTroll();
		
		if( troll == null ) {
			System.out.println( "There is no troll in this room!" );
			return false;
		}
		
		Axe axe = player.getAxe();
		if( axe == null ) {
			System.out.println( "You dont have an axe!" );
			return false;
		}
		
		if( troll.hasAxe() ) {
			
			room.addItem( new Axe() );
			troll.setAxe( null );
		}
		
		player.removeItem(axe);
		labyrinth.removeTroll(troll);
		room.removeTroll();
		
		return true;
	}
	
	public boolean usePotion() {
		
		if( player.isAtDoor() ) {
			
			Potion pot = player.getPotion();
			
			if( ! ( pot == null ) ) {
				
				Door door = player.getDoor();
				door.enchant();
				player.removeItem( pot );
				
			}
			
			else {
				System.out.println("You dont have any potions.");
				return false;
			}
			
		}
		
		else {
			System.out.println("You arent facing the door.");
			return false;
		}
		
		return true;
	}
	
	public boolean exit() {
		
		if( ! ( player.isAtDoor() ) ) {
			
			System.out.println( "You arent facing the door!!" );
			return false;
		}
		
		if( ! ( player.throughtDoor() ) ) {
			
			return false;
		}
		
		if( player.getPosition() == 0 ) {	// se saiu do labirinto
			
			if( player.getTreasure() == labyrinth.getTreasure() ) {
				
				System.out.println( "'''CONGRATULATIONS!! YOU WON!!'''" );
				gameOver = true;
			}
			
			else {
				
				System.out.println( "ARE YOU NUTS?? You cant go out!!" );
				System.out.println( "There is still treasure in the labyrinth!!" );
				player.throughtDoor();
				return false;
			}
		}
		
		System.out.println( "You went throught the door." );
		atualizeMap();
		return true;
	}
	
	public void giveUp() {
		
		
		
	}
	
	public boolean moveToItem(String type) {
		
		Room room = getRoom();
		Item item = room.getItem(type);

		if( type.equals( "gold" ) || type.equals( "treasure" ) ) {
			
			if( room.getTroll() != null ) {
				System.out.println( "There is a troll in the room. You cant reach the treasure.");
				return false;
			}
			
			Treasure treasure = room.getTreasure();
			player.setInteraction( treasure );
			player.setDoor(null);
			
			return true;
		}
		
		if( item == null ) {
			System.out.println( "There is no such item in the room." );
			return false;
		}
		
		player.setInteraction(item);
		player.setDoor(null);
		
		return true;
	}
	
	public boolean drop(String type) {
		
		Room room = getRoom();
		Item item = player.getItem(type);
		
		if( item == null ) {
			System.out.println( "You dont have that item!" );
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
			System.out.println( "There is no such door in this room!" );
			return false;
		}
		
		int id = labyrinth.getDoorID(door);
		
		if( id == 0 )
			System.out.println( "You see a big 'EXIT?' painted with blood on the door." );
		else
			System.out.println( "You see a big '" + id + "' painted with blood on the door." );
		
		player.setDoor(door);
		player.setInteraction(null);
		
		return true;
	}
	
	//
	
	private Room getRoom() {
		
		return labyrinth.getRoom( player.getPosition() );
	}
	
	private void trollAttack() {
		
		Room room = getRoom();
		Troll troll = null;
		
		if( room.hasTroll() ) {
			
			troll = room.getTroll();
			
			if( troll.hasAxe() ) {

				Item potion = player.getItem( "potion" );
				
				if( ! ( potion == null ) ) {

					System.out.println( "You barely entered the room and...." );
					System.out.println( "WOW! The troll throwed an axe at you, but you defended yourself with a potion(?!)." ); 
					player.removeItem( potion );
				}
				
				else {

					System.out.println( "You barely entered the room and...." );
					System.out.println( "WOW! The troll hitted you with an axe. You lost your savings." );
					int value = player.getTreasure();
					room.addTreasure( value );
					player.removeTreasure();
					
				}
				
				troll.setAxe( null );
				
			}
		}
	}
	
}