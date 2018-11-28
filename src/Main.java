public class Main{
	public static void main(String[] args) {
		
		//Instantiate components, and give them references to each other
		View viz = new View();
        Model game = new Model(viz);
		Controller cont = new Controller(game);
		viz.setCont(cont);
		
		//Start the game
		game.generateBoard();
		
    }
}