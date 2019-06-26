public class DoorLabel {
	
	private String label;
	private Door door;
	
	public DoorLabel(String label, Door door) {
		
		this.label = label;
		this.door = door;
	}
	
	public String getLabel() {
		
		return this.label;
	}
	
	public Door getDoor() {
		
		return this.door;
	}
}