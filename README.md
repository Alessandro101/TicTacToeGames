# Tic Tac Toe Game Suite

Game application that allows a user to play either tic tac toe or number scrabble using a GUI.

## Description

Game Suite is created using several packages. One package contains boardgame classes, one package contains the main GUI, one package contains the classes for TicTacToe and Numerical TicTacToe, and one package contains a class representing a player. Each game is comprised of three classes, a game class, a board class and a view class. The game class extends the BoardGame class to override methods that will be used while the game is running (e.g. inputting a value onto a grid) as well as extending the Saveable interface to allow saving/loading of files. The board class defines win conditions, tie conditions and exception handling methods. The view class is added to the main GUI in order to display the game in a graphical setting. Regular TicTacToe can also be played in the System using a TextUI.

## Getting Started

### Dependencies

* Gradle is a prequisite needed to build the program

### Executing program

Building and running the program:

1.
```
gradle build
```

2.
```
gradle run
```

3. To run TextUI
```
java -cp build/classes/java/main tictactoe.TextUI
```
4. To run GUI
```
java -jar build/libs/A3.jar
```
Expected output for TextUI:
```
Welcome to Tic Tac Toe!





Player 1 is about to move
Enter a row number (1-3):
```

## Limitations

The program will not check for valid input files when loading games or profiles


## Author Information

* Name: Alessandro Arezza
* Email: aarezza@uoguelph.ca

## Development History
* 1.9
    * added Javadocs to each class file
* 1.8 
    * created player class for saving/loading profiles, added methods to GUI to update player statistics after each game, added functionality to menubar
* 1.7
    * Edited ways in which exception messages are displayed for numerical tic tac toe
* 1.6
    * created JPanel and methods for both games, created save/load menu for game boards, created main GUI, updated win conditions for numTTT
* 1.5
    * created UI for main interface and tictactoe game
* 1.4
    * fixed errors with Num TTT win conditions, changed way in which player swaps occur in both games
* 1.3
    * created files and methods for numerical tic tac toe, adjusted save methods to files
* 1.2
    * added static methods for saving/loading files, added parser to Game, changed ways in which strings are compared in Board
* 1.1
    * Update TicTacToeGame.java
* 1.0
    * Update TicTacToeBoard.java
* 0.9
    * created view for tic tac toe gui, changed names of files
* 0.8
    * added ability to start new game in TextUI, allowed player switches to be made in game class, finished tie method
* 0.7
    * Implemented methods to check for wins in board class, implemented ways to switch players
* 0.6
    * removed call to check win
* 0.5
    * added win conditions to game, started testing methods in textUI
* 0.4
    * created classes for board, game rules and text ui
* 0.3
    * deleted tictactoe.java
* 0.2
    * changed name of tic tac toe game class
* 0.1
    * created package for tic tac toe game

## Acknowledgments

Inspiration, code snippets, etc.
* [exampleguiproject](https://gitlab.socs.uoguelph.ca/examples/exampleguiproject.git)
* [oracle](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html)
* [codejava](https://www.codejava.net/java-se/swing/add-file-filter-for-jfilechooser-dialog)
