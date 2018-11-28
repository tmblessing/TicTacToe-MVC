import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View{
	
	private JFrame gui;
	private JPanel gameBoard;
	private JPanel gamePanel;
	private JPanel options;
	private JPanel messages;
	private JButton reset;
    private JTextArea playerturn;
	private JButton[][] buttons;
	private Controller cont;
	
	public View(){
		
		cont = null;
		
		gui = new JFrame("Tic Tac Toe");
		reset = new JButton("Reset");
		playerturn= new JTextArea();
		buttons = new JButton[3][3];
		
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(new Dimension(500, 350));
		gui.setResizable(true);

		gamePanel = new JPanel(new FlowLayout());
		gameBoard = new JPanel(new GridLayout(3,3));
		gamePanel.add(gameBoard, BorderLayout.CENTER);

		options = new JPanel(new FlowLayout());
		options.add(reset);
		messages = new JPanel(new FlowLayout());
		messages.setBackground(Color.white);

		gui.add(gamePanel, BorderLayout.NORTH);
		gui.add(options, BorderLayout.CENTER);
		gui.add(messages, BorderLayout.SOUTH);

		messages.add(playerturn);
		playerturn.setText("Player 1 to play 'X'");
		
		reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cont.resetPressed();
            }
        });
	}

	//Create gui objects a cell at the specified cord
	public JButton newCell(int row, int column){
		this.buttons[row][column] = new JButton();
		this.buttons[row][column].setPreferredSize(new Dimension(75,75));
		this.buttons[row][column].setText("");
		this.buttons[row][column].addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) {
				for(int x = 0; x < 3 ; x++) {
					for(int y = 0; y<3 ; y++) {
						 if(e.getSource() == buttons[x][y] && buttons[x][y].getText() == ""){
							cont.cellClicked(x,y);
						 }
					}
				}
			  } 
		} );
		this.gameBoard.add(this.buttons[row][column]);
		return this.buttons[row][column];
	}
	
	//For the player turn text box. 0 = X to move, 1 = Y to move, 2 = X win, 3 = Y win, 4 = Draw
	public void setMessage(int status){
		if(status == 0){ this.playerturn.setText("Player 1 to play 'X'");}
		else if(status == 1){ this.playerturn.setText("Player 2 to play 'O'");}
		else if(status == 2){ this.playerturn.setText("Player 1 wins!");}
		else if(status == 3){ this.playerturn.setText("Player 2 wins!");}
		else if(status == 4){ this.playerturn.setText("Game ends in a draw");}
		else{ System.out.println("ERROR: Illegal argument error on View.changeMessage");}
	}
	
	//Clear game board
	public void clearBoard(){ this.gameBoard.removeAll(); }
	
	//Update view to new model state
	public void updateBoard(int x, int y, char z){
		this.buttons[x][y].setText("" + z); //Just a trick to convert char to string
	}
	
	//Set the controller
	public void setCont(Controller arg){ this.cont = arg; }
	
	//Show the view, once everything is ready
	//Return is just for testing purposes
	public boolean makeVisable(){ 
		gui.setVisible(true); 
		return true;
	}

}