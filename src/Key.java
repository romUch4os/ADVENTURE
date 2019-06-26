public class Key extends Item {
	
	int id;
	Door fromDoor;
	
	public Key(int id, Door door) {
		
		this.type = "key";
		this.id = id;
		this.fromDoor = door;
	}
	
	public Door fromDoor() {
		
		return this.fromDoor;
	}
	
	public String getDescription() {
		
		String s = super.getDescription();
		s = s + " " + id;
		return s;
	}
	
}