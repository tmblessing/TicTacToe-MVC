import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller {
	
	private Model game;
	
	public Controller(Model arg){
		
		game = arg;
		
	}
	
	//Recieves arguments of which box on the view was clicked
	//Checks to see if the game already ended, if not, tells model which box to change
	public void cellClicked(int x, int y){
		
		if(this.game.noMovesLeft()){ //If the game is over, don't do anything
			return; 
		}
		
		this.game.blockClaimed(x,y); //Tell the model that a block was claimed

		this.game.endTurn(); //Also, end the turn
	}
	
	//Identical to above, but does not use any model methods that try to update the view
	//Can be used to play without any view
	public void testCellClicked(int x, int y){
		
		if(this.game.noMovesLeft()){ //If the game is over, don't do anything
			return; 
		}
		
		this.game.testMove(x,y); //Tell the model that a block was claimed

		this.game.testEndTurn(); //Also, end the turn
	}
	
	public void resetPressed(){ game.resetGame(); }
	
	public void setModel( Model newModel){ this.game = newModel; }
	
	public Model getModel(){ return game; }
}