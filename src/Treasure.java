public class Treasure extends Item {
	
	private int amount;
	
	public Treasure() {
		
		this.type = "treasure";
		this.amount = 0;
	}
	
	public Treasure(int amount) {
		this();
		this.amount = amount;
	}
	
	public void addAmount(int amount) {
		
		this.amount = this.amount + amount;
	}
	
	public int getAmount() {
		
		return this.amount;
	}
}