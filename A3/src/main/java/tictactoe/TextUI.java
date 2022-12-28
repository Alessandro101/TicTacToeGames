package tictactoe;

import java.util.Scanner;

/**
This class represents the TextUI for the standard tic tac toe game. This class
uses the system to display the game and interact with the user using scanners.

@author Alessandro Arezza

*/
public class TextUI{
    private TicTacToeGame game;
    private Scanner input;
    private int userRow;
    private int userCol;
    private String symbol;
    private int choice = 0;
    private String loadFile;
    private String saveFile;

    public TextUI(){
        choice = 0;
        input = new Scanner(System.in);
        game = new TicTacToeGame(3,3);
    }

    public static void main(String[] args){
        TextUI gameUI = new TextUI();
        int game = 0;
        gameUI.setSymbol();
        System.out.println("\nWelcome to Tic Tac Toe!\n");
        while (game == 0){
            gameUI.displayGrid();
            gameUI.displayStateMessage();
            gameUI.getInput();
            if (gameUI.gameDone()){
                gameUI.displayGrid();
                gameUI.displayStateMessage();
                if (gameUI.startNewGame()){
                    game = 0;
                }else{
                    game = 1;
                }
            }else{
                gameUI.setSymbol();
            }
        }
    }

    /**
    Will change the symbol depending on the current player
    */
    public void setSymbol(){
        if(game.getCurrentPlayer() == 1){
            symbol = "X";
        }else{
            symbol = "O";
        }
    }
    public boolean gameDone(){
        return game.isDone();
    }

    public void displayGrid(){
        System.out.println(game);
    }

    public void displayStateMessage(){
        System.out.println(game.getGameStateMessage());
    }

    /**
    Asks user for row number and column number in order to insert symbol into grid
    */
    public void getInput(){
        System.out.println("Enter a row number (1-3): ");
        userRow = input.nextInt();
        System.out.println("Enter a column number (1-3): ");
        userCol = input.nextInt();
        game.takeTurn(userCol,userRow,symbol);
    }   

    /**
    Asks user if they would like to start a new game
    */
    public boolean startNewGame(){
        System.out.println("Would you like to start a new game? (Enter 1 to restart): ");
        choice = input.nextInt();
        if (choice == 1){
            game.newGame();
            game.resetConditions();
            setSymbol();
            return true;
        }else{
            return false;
        }
    }

}