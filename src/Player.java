import java.util.Collection;
import java.util.HashSet;

public class Player {
	
	private String name;
	private int position;
	private Collection<Item> items = new HashSet<Item>();;
	private Item interaction;
	
	public Player(String name) {
		
		this.name = name;
		this.position = 1;
		this.interaction = null;
	}
	
	public String getName() {
		
		return this.name;
	}
	
	public int getPosition() {
		
		return this.position;
	}
	
	public void setPosition(int position) {
		
		this.position = position;
	}
	
	public Item getInteraction() {
		
		return this.interaction;
	}
	
	public void setInteraction(Item item) {
		
		this.interaction = item;
	}
	
	public void addItem(Item item) {
		
		items.add(item);
	}
	
	public boolean isInteracting(String item) {
		
		if( this.interaction == null )
			return false;
		
		if( this.interaction.getType().equals(item) )
			return true;
		
		return false;
	}
	
	
	
	
	
	public void moveTo(Labyrinth lab, int goTo) {
		
		Room r = lab.getRoom(this.position);
		
		this.position = r.getDestiny(goTo);
		
	}
	
	
}