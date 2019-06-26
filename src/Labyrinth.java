import java.util.Collection;
import java.util.HashSet;

public class Labyrinth {
	
	private Room[] rooms;
	private Door[] doors;
	private Treasure treasure = new Treasure();
	private Collection<Troll> trolls = new HashSet<Troll>();
	
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
	}
	
	
	public Room getRoom(int id) {
		
		return this.rooms[id];
	}
	
	
	public int getTreasure() {
		
		return treasure.getAmount();
	}
	
	public void removeTroll(Troll troll) {
		
		trolls.remove(troll);
	}
	
}