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
	
	public boolean hasAxe() {
		
		if( axe== null )
			return false;
		
		return true;
	}
	
	public void setAxe(Axe axe) {
		
		this.axe = axe;
	}
	
	public Axe getAxe() {
		
		return axe;
	}
	
}