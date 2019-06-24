import java.util.Scanner;

public class GameFacade {
	
	private static GameFacade instance = null;
	private Labyrinth labyrinth = null;
	private Player player = null;
	private Boolean gameOver;
	public static Scanner s = new Scanner(System.in);
	
	public static GameFacade getInstance() {
		
		if( instance == null )
			instance = new GameFacade();
		
		return instance;
	}
	
	private GameFacade() {
		
		System.out.print("Enter players name:\n/>");
		String name = s.next();
		player = new Player(name);
		
		labyrinth = new Labyrinth();
		gameOver = false;
	}
	
	public boolean isGameOver() {
		
		System.out.println("pausa");	s.next();
		return gameOver;
	}
	
	public void atualizeMap() {
		
	}
	
	public boolean pickUp(String type) {
		
		if( ! (player.isInteracting(type)) ) {
			System.out.println("Player is not interacting with that item!!");
			return false;
		}
		
		Item item = player.getInteraction();
		
		Room r = labyrinth.getRoom( player.getPosition() );
		
		r.removeItem(item);
		player.addItem(item);
		player.setInteraction(null);
		
		return true;
	}
	
	
	
	
	public void setItem(String item) {
		
		Room r = labyrinth.getRoom( player.getPosition() );
		
		Item i = r.getItem(item);
		player.setInteraction(i);
		
	}
	
	
	public int getPlayerPosition() {
		
		return player.getPosition();
	}
	
	public void describeRoom() {
		
		Room r = labyrinth.getRoom( player.getPosition() );
		
		System.out.println("Items: " + r.getItems());
		
	}
	
	public void playerMoveTo(int id) {
		
		Room r = labyrinth.getRoom( player.getPosition() );
		// colocar condicao do room 0 e gold igual pra acabar
		player.setPosition( r.getDestiny( id ) );
	}
	
	/*
	public void removeItem(String item) {
		
		Room r = labyrinth.getRoom( player.getPosition() );
		r.removeItem(item);
	}
	*/
	
}