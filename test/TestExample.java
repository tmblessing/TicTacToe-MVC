import org.junit.Test;
import static org.junit.Assert.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
	
    @Test
	//Testing the view on its own, with no model or controller
    public void testView(){
        View visualization = new View();
		//The jframe should display. It'll disapear almost immediatly, as these tests take less than a second to run
		visualization.makeVisable();
		assertEquals(true, visualization.makeVisable());
    }
	
	@Test
	//Testing the model on its own, with no view or controller
	public void testModel(){
		Model testGame = new Model(null);
		//The model should be reporting that moves can be made, as this would be a new game
		assertEquals(false, testGame.noMovesLeft());
	}
	
	@Test
	//Testing the controller on its own
	public void testController(){
		Controller testControl = new Controller(null);
		//Controller can exist on it's own but can't do much
		//This controller should tell us that there is no Model set
		assertEquals(null, testControl.getModel());
		
		//For a more interseting test of the model, we will play a game without the view
		Model testModel = new Model(null);
		testControl.setModel(testModel);
		
		testControl.testCellClicked(0,0);
		testControl.cellClicked(0,1);
		testControl.cellClicked(1,1);
		testControl.cellClicked(1,0);
		testControl.cellClicked(2,2);
		
		//The above moves make for a diagonal win, so even though only five moves have been made, there should be no moves left on in the model
		assertEquals(true, testModel.noMovesLeft());
	}
}
