import java.util.Scanner;

public class Program {

	static GameFacade game = GameFacade.getInstance();
	static String reader;
	public static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		while( ! ( game.isGameOver() ) ) {
			
			game.atualizeMap();
			
			game.describeRoom();
			
			game.setItem("potion");
			
			game.pickUp("apple");
			game.pickUp("potion");
			
			
			/*		// para fazer leitura dos comandos
			String[] splitted = reader.split(" ");
			commands(splitted.length, splitted);
			*/
			
		}
		
		System.out.println("Acabou. BYEBYE!!");
		
	}
	
	public static void commands(int size, String[] command) {

		// 1-sized
		if( size == 1 ) {
			
			switch( command[0] ) {
				
				case "view":
					//game.view();
					break;
					
				case "inventory":
					//game.inventory();
					break;
					
				case "exit":
					//game.exit();
					break;
					
				default:
					System.out.println("Invalid command!!");
					break;
			}
		}
		
		// 2-sized
		if( size == 2 ) {
			
			switch( command[0] ) {
				case "moveTo":
					break;
				
				case "pickUp":
					break;
					
				case "drop":
					break;
					
				case "throwAxe":
					break;
					
				default:
					System.out.println("Invalid command!!");
			}
			
		}
		
		//3-sized
		if( size == 3 ) {
			
			switch ( command[0] ) {
				
				case "moveTo":
					break;
					
				default:
					System.out.println("Invalid command!!");
			}
		}
	}
	
	
	
	
	
}