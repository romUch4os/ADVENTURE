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
	
	public boolean isGameOver() {
		
		return gameOver;
	}
	
	public void atualizeMap() {		// escrever atualizeMap
		
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
	
	public boolean exit() {
		
		if( ! ( player.isAtDoor() ) ) {
			
			System.out.println( "You arent facing the door!!" );
			return false;
		}
		
		if( ! ( player.throughtDoor() ) ) {
			
			return false;
		}
		
		if( player.getPosition() == 0 ) {	// se saiu do labirinto
			
			if( player.getTreasure() == labyrinth.getTreasure() )
				gameOver = true;
			
			else {
				
				System.out.println( "ARE YOU NUTS?? You cant go out!!" );
				System.out.println( "There is still treasure in the labyrinth!!" );
				player.throughtDoor();
				return false;
			}
		}
		
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
		
		player.removeItem(axe);
		labyrinth.removeTroll(troll);
		room.removeTroll();
		
		return true;
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
	
	public boolean pickUp(String type) {

		Room room = getRoom();
		
		if( type.equals( "gold" ) || type.equals( "treasure" ) ) {
			
			if( ! (player.isInteracting( "treasure" )) ) {
				System.out.println( "You are not in the treasure of the room." );
				return false;
			}
			
			Treasure treasure = room.getTreasure();
			
			player.addTreasure( treasure.getAmount() );
			player.setInteraction( null );
			room.removeTreasure();
			
			return true;
		}
	
		Item item = room.getItem( type );
		
		if( ! (player.isInteracting( type ))) {
			System.out.println( "You arent interacting with that item." );
			return false;
		}
		
		player.addItem(item);
		player.setInteraction( null );
		room.removeItem(item);
			
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
		
		player.setDoor(door);
		player.setInteraction(null);
		
		return true;
	}
	
	//
	
	private Room getRoom() {
		
		return labyrinth.getRoom( player.getPosition() );
	}
	
}