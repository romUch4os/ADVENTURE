public class Troll {
	
	Axe axe;
	int position;
	
	public Troll(int position) {
		
		this.position = position;
		axe = null;
	}
	
	public int getPosition() {
		
		return position;
	}
	
	public void setPosition(int position) {
		
		this.position = position;
	}
	
}