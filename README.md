# TicTacToe
TicTacToe.java is a MVC Java implementation of the well-known tic tac toe game

Model.java is the core of the game, and holds the main data structure, logic, and rules needed for the game

View.java holds all the GUI elements for the game. The model reports the board state to it, and any clicks made on it are reported to the controller

Controller.java accepts inputs from the user and has logic that converts the inputs into commands for the model

### How to build and test (from Terminal):

1. Make sure that you have Apache Ant installed.

2. Run `ant` in the root directory, which contains the `build.xml` build file.

3. Compiled java classes will be in the `bin` directory.

4. Run `ant test` to run all unit tests.

### How to run (from Terminal):

1. After building the project (i.e., running `ant`), run:
   `java -cp bin Main`

### How to clean up (from Terminal):

1. Run `ant clean` to clean the project (i.e., delete all generated files).
