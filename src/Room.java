import java.util.Collection;
import java.util.HashSet;

public class Room {
	
	private Collection<Door> doors = new HashSet<Door>();
	private Collection<Item> items = new HashSet<Item>();
	
	public Room(int destinyA) {
		
		doors.add( new Door("A", destinyA) );
	}
	
	public Room(int destinyA, int destinyB) {
		
		this( destinyA );
		doors.add( new Door("B", destinyB) );
	}
	
	public Room(int destinyA, int destinyB, int destinyC){
		
		this( destinyA, destinyB );
		doors.add( new Door("C", destinyC) );
	}
	
	// /\ construcao das salas (contrutores)
	
	public int getDestiny(int goTo) {
		
		if( goTo == 1 )
			for( Door d: doors )
				if( d.getId() == "A" )
					return d.getDestiny();
		
		if( goTo == 2 )
			for( Door d: doors )
				if( d.getId() == "B" )
					return d.getDestiny();
		
		if( goTo == 3 )
			for( Door d: doors )
				if( d.getId() == "C" )
					return d.getDestiny();
		
		return 5;
	}
	
	public int getNumDoors() {
		
		return doors.size();
		
	}
	
	public void addItem(Item item) {
		
		items.add(item);
	}
	
	public Collection<Item> getItems(){
		
		return items;
	}
	
	public void removeItem(Item item) {
		
		items.remove(item);		
	}
	
	public Item getItem(String item) {
		
		for( Item i: items )
			if( i.getType().equals(item) )
				return i;
		
		return null;
	}
	
	
	
	
	
}