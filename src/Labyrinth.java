import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Labyrinth {
	
	private Room[] rooms;
	private Door[] doors;
	private Treasure treasure = new Treasure();
	private Collection<Troll> trolls = new HashSet<Troll>();
	private Random r = new Random();
	
	public Labyrinth() {

		doors = new Door[21];
		doors[0] = new Door(4, 0);
		doors[1] = new Door(1, 2);
		doors[2] = new Door(2, 3);
		doors[3] = new Door(4, 5);
		doors[4] = new Door(1, 6);
		doors[5] = new Door(3, 8);
		doors[6] = new Door(5, 10);
		doors[7] = new Door(6, 7);
		doors[8] = new Door(9, 10);
		doors[9] = new Door(7, 12);
		doors[10] = new Door(8, 13);
		doors[11] = new Door(10, 15);
		doors[12] = new Door(11, 12);
		doors[13] = new Door(12, 13);
		doors[14] = new Door(14, 15);
		doors[15] = new Door(11, 16);
		doors[16] = new Door(14, 19);
		doors[17] = new Door(15, 20);
		doors[18] = new Door(16, 17);
		doors[19] = new Door(17, 18);
		doors[20] = new Door(18, 19);
		
		rooms = new Room[21];
		rooms[0] = null;
		rooms[1] = new Room(doors[4], doors[1]);
		rooms[2] = new Room(doors[1], doors[2]);
		rooms[3] = new Room(doors[2], doors[5]);
		rooms[4] = new Room(doors[0], doors[3]);
		rooms[5] = new Room(doors[6], doors[3]);
		rooms[6] = new Room(doors[4], doors[7]);
		rooms[7] = new Room(doors[9], doors[7]);
		rooms[8] = new Room(doors[10], doors[5]);
		rooms[9] = new Room(doors[8]);
		rooms[10] = new Room(doors[8], doors[11], doors[6]);
		rooms[11] = new Room(doors[12], doors[15]);
		rooms[12] = new Room(doors[12], doors[9], doors[13]);
		rooms[13] = new Room(doors[13], doors[10]);
		rooms[14] = new Room(doors[14], doors[16]);
		rooms[15] = new Room(doors[14], doors[11], doors[17]);
		rooms[16] = new Room(doors[15], doors[18]);
		rooms[17] = new Room(doors[18], doors[19]);
		rooms[18] = new Room(doors[20], doors[19]);
		rooms[19] = new Room(doors[16], doors[20]);
		rooms[20] = new Room(doors[17]);
		
		
		// colocar as paradas aleatorias
		for( int i = 1; i <= 20; i++ ) {
			
			rooms[i].addTreasure( r.nextInt(10) );
			
			if( r.nextInt(2) == 1 )
				rooms[i].addItem( new Axe() );
			
			if( r.nextInt(2) == 1 )
				rooms[i].addItem( new Potion() );
			
			if( r.nextInt(4) == 1 ) {
				
				Troll troll = new Troll(i);
				rooms[i].addTroll(troll);
				trolls.add(troll);
				
			}
			
			if( r.nextInt(9) == 1 ) {
				
				Key key = new Key(i, doors[i]);
				
				int room = r.nextInt(19) + 1;
				
				while( room == doors[i].getRoomA() || room == doors[i].getRoomB() )
					room = r.nextInt(19) + 1;
				
				rooms[room].addItem(key);
				
			}
			
		}
		
	}
	
	
	public Room getRoom(int id) {
		
		return this.rooms[id];
	}
	
	public int getDoorID(Door door) {
		
		for( int i = 0; i <= 15; i++ )
			if( door.equals( doors[i] ) )
				return i;
		
		return -1;
	}
	
	
	public int getTreasure() {
		
		return treasure.getAmount();
	}
	
	public Collection<Troll> getTrolls(){
		
		return trolls;
	}
	
	public void removeTroll(Troll troll) {
		
		trolls.remove(troll);
	}
	
}