import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Model {
	
    private char[][] blocks;
    private char status;
    private char player;
    private int movesLeft;
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
	
	//For the controller to update the board
	public void blockClaimed( int x, int y ){
		this.blocks[x][y] = player;
		this.visualization.updateBoard( x, y, player);
	}
	
	//When a user ends their turn, the controller will fire this. Checks to see if someone won, and then as long as there are moves left, it ends the game
	public void endTurn(){
		
		if( this.isFinished() ) {
			if( this.player == 'X' ){
				visualization.setMessage(2);
				this.movesLeft = 0;
				return;
			}
			visualization.setMessage(3);
			this.movesLeft = 0;
			return;
		}
		
		
		
		//Draw
		if( this.movesLeft == 1 ){
			this.visualization.setMessage(4);
			this.movesLeft = 0;
			return;
		}
		
		this.movesLeft--;
		
		//Swap player, and tell the view we are swapping player
		if( this.player == 'X'){
			this.visualization.setMessage(1);
			this.player = 'O';
			return;
		}
		this.visualization.setMessage(0);
		this.player = 'X';
	}
	
	//Returns true if the board has been won
	private boolean isFinished(){ 
	
		//Rows
		for( int row = 0; row < 3; row++ ){
			if( this.blocks[row][0] == this.blocks[row][1] && this.blocks[row][0] == this.blocks[row][2] && this.blocks[row][0] != ' '){
				return true;
			}
		}
		
		//Cols
		for (int col = 0; col < 3; col++) {
			if (this.blocks[0][col] == this.blocks[1][col] && this.blocks[0][col] == this.blocks[2][col] && this.blocks[0][col] != ' ') {
				return true;
			}
		}
		
		//Diags
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
	public void testEndTurn(){
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