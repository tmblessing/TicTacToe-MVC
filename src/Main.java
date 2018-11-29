public class Main{
	public static void main(String[] args) {
		
		//Instantiate components, and connect them
		View viz = new View();
        	Model game = new Model(viz); 		//Model updates the view
		Controller cont = new Controller(game); //Controller updates the model
		viz.setCont(cont);			//And the view updates the controller
		
		//Start the game
		game.generateBoard();
		
    }
}
