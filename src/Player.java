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
			System.out.println( "\tYou cant carry anymore items." );
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
	
	public Potion getPotion() {
		
		for( Item i: items )
			if( i instanceof Potion )
				return (Potion) i;
		
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

		// verifica se a porta esta trancada 
		if( atDoor.isLocked() ) {
			
			System.out.println( "\tThe door is locked." );
			
			Key key = null;
			
			// verifica se tem a chave para abrir a prota
			for( Item i: items ) {
				if( i instanceof Key )
					if( ((Key) i).fromDoor().equals(atDoor) ) {
						
						System.out.println( "\tYou ve unclocked it.");
						key = (Key) i;
						atDoor.unlock();
					}
			}
			
			if( atDoor.isLocked() ) {
				System.out.println( "\tYou dont have the key." );
				return false;
			}
			
			items.remove( key );
		}
		
		// verifica se a porta esta encantada e abre
		if( atDoor.isEnchanted() ) {
			
			System.out.println( "\tThe door was enchanted. You ve unenchanted it." );
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