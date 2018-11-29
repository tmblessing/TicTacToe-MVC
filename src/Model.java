import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//The model is the central part of the MVC architectural pattern, holding the game's data structure, manages the logic and holds the rules for the application
public class Model {
	
    private char[][] blocks;		//Main data structure, holds the board state
    private char player;		//Who's turn it is, cna be 'X' or 'Y'
    private int movesLeft;		//How many moves are left, 0 means game has ended
	private View visualization;
	
	public Model(View arg) {
		
		blocks = new char[3][3];
		player = 'X';
		movesLeft = 9;
		visualization = arg;
        
    }
	
	//Instantiate the primary data struct, and tell the view to create gui objects for each item in that struct
	public void generateBoard(){
		for( int row = 0; row<3 ;row++ ){
            for( int column = 0; column<3 ;column++ ){
				this.blocks[row][column] = ' ';
                visualization.newCell(row, column);
            }
        }
		visualization.makeVisable();
	}
	
	//Updates the board
	public void blockClaimed( int x, int y ){
		this.blocks[x][y] = player;
		this.visualization.updateBoard( x, y, player);
	}
	
	//Command to end the turn and advance the game state state
	//Also checks for game-end conditions
	public void endTurn(){
		
		this.movesLeft--;
		
		//Check for victory
		if( this.isFinished() ) {                    //isFinished returns true if a win condition exists
			if( this.player == 'X' ){
				visualization.setMessage(2); //2 = X win message
				this.movesLeft = 0;          //Locks board
				return;
			}
			visualization.setMessage(3);	     //3 = O win message
			this.movesLeft = 0;                  //Locks board
			return;
		}
		
		//Draw
		if( this.movesLeft == 0 ){
			this.visualization.setMessage(4);    //4 = Draw message
			return;
		}
		
		
		
		//Swap player, and tell the view the new look it should have
		if( this.player == 'X'){
			this.visualization.setMessage(1);    //O = O turn message
			this.player = 'O';
			return;
		}
		this.visualization.setMessage(0);            //1 = X turn message
		this.player = 'X';
	}
	
	//Returns true if the board has been won
	private boolean isFinished(){ 
	
		//Check for horizontal wins
		for( int row = 0; row < 3; row++ ){
			if( this.blocks[row][0] == this.blocks[row][1] && this.blocks[row][0] == this.blocks[row][2] && this.blocks[row][0] != ' '){
				return true;
			}
		}
		
		//Check for vertical wins
		for (int col = 0; col < 3; col++) {
			if (this.blocks[0][col] == this.blocks[1][col] && this.blocks[0][col] == this.blocks[2][col] && this.blocks[0][col] != ' ') {
				return true;
			}
		}
		
		//Check for diagonal wins
		if (this.blocks[0][0] == this.blocks[1][1] && this.blocks[0][0] == this.blocks[2][2] && this.blocks[0][0] != ' '|| this.blocks[2][0] == this.blocks[1][1] && this.blocks[2][0] == this.blocks[0][2] && this.blocks[2][0] != ' '){
			return true;
		}
		
		return false;
	
	}
	
	
	
	//Reset the game
    	public void resetGame() {
		this.visualization.clearBoard();
		this.generateBoard();
        	this.player = 'X';
        	this.movesLeft = 9;
        	this.visualization.setMessage(0);
    	}
	
	public char getPlayer(){ return this.player; }
	
	public boolean noMovesLeft(){
		if(this.movesLeft > 0){
			return false;
		}
		return true;
	}
	
	//Identical to blockClaimed, just without any command to update the view
	//Can be used to play without a view
	public void testMove(int x, int y){
		this.blocks[x][y] = player;
	}
	
	//Test method for playing without view
	public void noViewEndTurn(){
		if( this.isFinished() ) {
			if( this.player == 'X' ){
				this.movesLeft = 0;
				return;
			}
			this.movesLeft = 0;
			return;
		}
		if( this.movesLeft == 1 ){
			this.movesLeft = 0;
			return;
		}
		this.movesLeft--;
		if( this.player == 'X'){
			this.player = 'O';
			return;
		}
		this.player = 'X';
	}
}
