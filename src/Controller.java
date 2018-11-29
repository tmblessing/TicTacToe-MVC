import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//The controller is the component that accepts inputs from the user and has logic that converts the inputs into commands for the model
//The scope of possible inputs is very small, so the controller is also very small
public class Controller {
	
	private Model game;
	
	public Controller(Model arg){
		
		game = arg;
		
	}
	
	//Recieves where the user clicked on the board
	//Checks to see if the game already ended, if not, tells model which box to change, then ends the turn
	public void cellClicked(int x, int y){
		
		if(this.game.noMovesLeft()){ //If the game is over, don't do anything
			return; 
		}
		
		this.game.blockClaimed(x,y); //Tell the model that a block was claimed

		this.game.endTurn(); //Command the model to change into the next turn
	}
	
	//For testing purposes: Identical to above, but skips the view
	//Could be used to play this implimentation of tic tac toe from the command line, for example
	public void testCellClicked(int x, int y){
		
		if(this.game.noMovesLeft()){ //If the game is over, don't do anything
			return; 
		}
		
		this.game.testMove(x,y); //Tell the model that a block was claimed

		this.game.noViewEndTurn(); //Command the model to change into the next turn
	}
	
	public void resetPressed(){ game.resetGame(); } //Command model to rest itself
	
	public void setModel( Model newModel){ this.game = newModel; }
	
	public Model getModel(){ return game; }
}
