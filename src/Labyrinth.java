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
		
		
		// add randomly items/trolls in the labyrinth
		for( int i = 1; i <= 20; i++ ) {
			
			rooms[i].addTreasure( r.nextInt(11) );	// 0-10 treasure pieces/room
			
			if( r.nextInt(3) == 1 )		// 1/3 chance axe/room
				rooms[i].addItem( new Axe() );
			
			if( r.nextInt(3) == 1 )		// 1/3 chance potion/room
				rooms[i].addItem( new Potion() );
			
			if( r.nextInt(6) == 1 ) {	// 1/6 chance troll/room
				
				Troll troll = new Troll(i);
				rooms[i].addTroll(troll);
				trolls.add(troll);
				
			}
			
			if( r.nextInt(10) == 1 ) {	// 10% chance locked door
				
				Key key = new Key(i, doors[i]);
				doors[i].lock();
				
				int room = r.nextInt(20) + 1;
				
				while( room == doors[i].getRoomA() || room == doors[i].getRoomB() )
					room = r.nextInt(20) + 1;
				
				rooms[room].addItem(key);
				
			}
			
		}
		
	}
	
	public Room getRoom(int id) {
		
		return this.rooms[id];
	}
	
	public int getDoorID(Door door) {
		
		for( int i = 0; i <= 20; i++ )
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
	
	public void moveTrolls() {
		
		for( Troll troll : trolls ) {
			
			// sala q se encontra o troll
			Room room = getRoom( troll.getPosition() );
			
			// portas da sala, com seus label (A, B, C)
			Collection<DoorLabel> labeled = room.getDoors();
			
			Door[] doors = new Door[ labeled.size() ];
			int index = 0;
			
			// doors = portas que o troll pode atravessar
			for( DoorLabel d: labeled )
				
				if( ! ( d.getDoor().isEnchanted() || d.getDoor().isLocked() ) ) {
					
					Door door = d.getDoor();
					
					if( ! (door.equals( doors[0]) ) ) {
						
						doors[index] = d.getDoor();
						index++;
					}
				}
			
			// seleciona um random, indicando a porta q o troll vai
			Random r = new Random();
			int movement = r.nextInt( index );

			Door goTo = doors[movement];
			int nextRoom = goTo.goThrought( troll.getPosition() );
			
			// verifica se o troll pode ir pra proxima sala; move o troll
			if( ! ( nextRoom == 0 ) )
				
				if( ! ( rooms[nextRoom].hasTroll() ) ) {
						
					room.removeTroll();
					rooms[nextRoom].addTroll(troll);
					troll.setPosition(nextRoom);
					
				}
				
		}
		
	}
	
	public void equipTrolls() {
		
		// equipa os trolls com machado, caso estejam disponiveis nas salas
		for( Troll troll: trolls ) {
		
			Room room = getRoom( troll.getPosition() );
			
			Item axe = room.getItem( "axe" );
			
			if( ! ( axe == null || ! ( axe instanceof Axe ) ) ) {
				troll.setAxe( (Axe) axe );
				room.removeItem(axe);
			}
		}
	}
	
}