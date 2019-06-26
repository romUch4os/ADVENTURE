import java.util.Collection;
import java.util.HashSet;

public class Player {
	
	private int position;
	private Collection<Item> items = new HashSet<Item>();;
	private Item interaction;
	private Door atDoor;
	private Treasure treasure;
	
	public Player() {
		
		this.position = 1;
		this.interaction = null;
		this.atDoor = null;
		this.treasure = new Treasure();
	}
	
	public int getPosition() {
		
		return this.position;
	}
	
	public void setPosition(int position) {
		
		this.position = position;
	}
	
	public boolean isInteracting(String type) {
		
		if( interaction == null )
			return false;
		
		if( interaction.getType().equals( type ))
			return true;
		
		return false;
	}
	
	public Item getInteraction() {
		
		return this.interaction;
	}
	
	public void setInteraction(Item item) {
		
		this.interaction = item;
	}
	
	public void addTreasure(int amount) {
		
		if( treasure == null )
			treasure = new Treasure( amount );
		else
			treasure.addAmount( amount );
	}
	
	public int getTreasure() {
		
		return treasure.getAmount();
	}
	
	public void removeTreasure() {
		
		treasure = new Treasure();
	}
	
	public boolean addItem(Item item) {
		
		if( items.size() >= 5 ) {
			System.out.println( "You cant carry anymore items." );
			return false;
		}
		
		items.add(item);
		return true;
	}
	
	public void removeItem(Item item) {
		
		items.remove(item);
	}
	
	public Item getItem(String type) {
		
		for( Item i: items )
			if( i.getType().equals( type ))
				return i;
		
		return null;
	}
	
	public Axe getAxe() {
		
		for( Item i: items )
			if( i instanceof Axe )
				return (Axe) i;
		
		return null;
	}
	
	public Collection<Item> getItems() {
		
		return items;
	}
	
	public boolean isAtDoor() {
		
		if( atDoor == null )
			return false;
		
		return true;
	}
	
	public boolean throughtDoor() {

		if( atDoor.isLocked() ) {
			
			System.out.println( "The door is locked." );
			
			Key key = null;
			
			for( Item i: items ) {
				if( i instanceof Key )
					if( ((Key) i).fromDoor().equals(atDoor) ) {
						
						System.out.println( "You ve unclocked it.");
						key = (Key) i;
						atDoor.unlock();
					}
			}
			
			if( atDoor.isLocked() ) {
				System.out.println( "You dont have the key." );
				return false;
			}
			
			items.remove( key );
		}
		
		if( atDoor.isEnchanted() ) {
			
			System.out.println( "The door was enchanted. You ve unenchanted it." );
			atDoor.unenchant();
		}
		
		int destiny = atDoor.goThrought( getPosition() );
		setPosition( destiny );
		
		return true;
	}
	
	public Door getDoor() {
		
		return atDoor;
	}
	
	public void setDoor(Door door) {
		
		this.atDoor = door;
	}
	
}