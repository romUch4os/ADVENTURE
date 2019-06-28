import java.util.Collection;
import java.util.HashSet;

public class Room {
	
	private Collection<DoorLabel> doors = new HashSet<DoorLabel>();
	private Collection<Item> items = new HashSet<Item>();
	Troll troll;
	Treasure treasure = null;
	
	public Room(Door doorA) {
		
		troll = null;
		doors.add( new DoorLabel("A", doorA) );
	}
	
	public Room(Door doorA, Door doorB) {
		
		this( doorA );
		doors.add( new DoorLabel("B", doorB) );
	}
	
	public Room(Door doorA, Door doorB, Door doorC){
		
		this( doorA, doorB );
		doors.add( new DoorLabel("C", doorC) );
	}
	
	public int getNumDoors() {
		
		return doors.size();
	}
	
	public Door getDoor(String doorID) {
		
		for( DoorLabel door: doors )
			if( door.getLabel().equals( doorID ) )
				return door.getDoor();
		
		return null;
	}
	
	public Collection<DoorLabel> getDoors(){
		
		return doors;
	}
	
	public String getDoorLabel(Door door) {
		
		for( DoorLabel d: doors )
			if( d.getDoor().equals( door ) )
				return d.getLabel();
		
		return null;
	}
	
	public void addTreasure(int amount) {
		
		if( treasure == null )
			treasure = new Treasure( amount );
		else
			treasure.addAmount( amount );
	}
	
	public Treasure getTreasure() {
		
		return treasure;
	}
	
	public void removeTreasure() {
		
		treasure = null;
	}
	
	public boolean hasTroll() {
		
		if( troll == null )
			return false;
		return true;
	}
	
	public void addTroll(Troll troll) {
		
		this.troll = troll;
	}
	
	public Troll getTroll() {
		
		return troll;
	}
	
	public void removeTroll() {
		
		troll = null;
	}
	
	public void addItem(Item item) {
		
		items.add(item);
	}
	
	public Item getItem(String type) {
		
		for( Item i: items )
			if( i.getType().equals( type ) )
				return i;
		
		return null;
	}
	
	public Collection<Item> getItems(){
		
		return items;
	}
	
	public void removeItem(Item item) {
		
		items.remove(item);		
	}
	
}