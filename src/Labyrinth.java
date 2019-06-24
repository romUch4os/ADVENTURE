public class Labyrinth {
	
	private Room[] rooms;
	
	public Labyrinth() {
		
		rooms = new Room[21];
		rooms[0] = null;
		rooms[1] = new Room(6, 2);
		rooms[2] = new Room(1, 3);
		rooms[3] = new Room(2, 8);
		rooms[4] = new Room(0, 5);
		rooms[5] = new Room(10, 4);
		rooms[6] = new Room(1, 7);
		rooms[7] = new Room(12, 6);
		rooms[8] = new Room(13, 3);
		rooms[9] = new Room(10);
		rooms[10] = new Room(9, 15, 5);
		rooms[11] = new Room(12, 16);
		rooms[12] = new Room(11, 7, 13);
		rooms[13] = new Room(12, 8);
		rooms[14] = new Room(15, 19);
		rooms[15] = new Room(14, 10, 20);
		rooms[16] = new Room(11, 17);
		rooms[17] = new Room(16, 18);
		rooms[18] = new Room(19, 17);
		rooms[19] = new Room(14, 18);
		rooms[20] = new Room(15);
		
		// colocar as paradas aleatorias
		Axe axe1 = new Axe();
		Axe axe2 = new Axe();
		Potion potion1 = new Potion();
		rooms[1].addItem(axe1);
		rooms[1].addItem(axe2);
		rooms[1].addItem(potion1);
	}
	
	public Room getRoom(int id) {
		
		return this.rooms[id];
	}
	
	public void describeRoom(int id) {
		
		System.out.print("Numero de portas da sala " + id);
		System.out.println(": " + rooms[id].getNumDoors() );
	}
	
}