public class Door {
	
	private int roomA, roomB;
	private boolean locked = false;
	private boolean enchanted = false;
	
	public Door(int roomA, int roomB) {
		
		this.roomA = roomA;
		this.roomB = roomB;
	}
	
	public boolean isLocked() {
		
		return locked;
	}
	
	public void lock() {
		
		locked = true;
	}
	
	public void unlock() {
		
		locked = false;
	}
	
	public boolean isEnchanted() {
		
		return enchanted;
	}
	
	public void enchant() {
		
		enchanted = true;
	}
	
	public void unenchant() {
		
		enchanted = false;
	}
	
	public int goThrought(int position) {
		
		if( position == roomA )
			return roomB;
		
		if( position == roomB )
			return roomA;
		
		return position;
	}
	
}
