package tictactoe;

import java.util.Scanner;

public class NumTextUI{
    private NumTTTGame game = new NumTTTGame(3,3);
    private Scanner input = new Scanner(System.in);
    private int userRow;
    private int userCol;
    private String symbol;
    private int choice = 0;
    private String loadFile;
    private String saveFile;
    private String userNum;

    public static void main(String[] args){
        NumTextUI gameUI = new NumTextUI();
        int game = 0;
        System.out.println("\nWelcome to Numerical Tic Tac Toe!\n");
        gameUI.loadGame();
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
            }
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

    public void getInput(){
        System.out.println("Enter a row number (1-3): ");
        userRow = input.nextInt();
        System.out.println("Enter a column number (1-3): ");
        userCol = input.nextInt();
        if (game.getCurrentPlayer() == 1){
            System.out.println("Enter an odd number: ");
            userNum = input.next();
        }else{
            System.out.println("Enter an even number: ");
            userNum = input.next();     
        }
        game.takeTurn(userCol,userRow,userNum);
    }   

    public boolean startNewGame(){
        System.out.println("Would you like to start a new game? (Enter 1 to restart): ");
        choice = input.nextInt();
        if (choice == 1){
            game.newGame();
            game.resetConditions();
            return true;
        }else{
            return false;
        }
    }

    public void loadGame(){
        System.out.println("Enter a file to load (enter 'none' if you don't have a file): ");
        loadFile = input.next();
        if (loadFile.equals("none")){
          return;
        }else{
          SaveToFile.load(game, loadFile, "assets");
    }
}

    public void saveGame(){
        System.out.println("Enter a file to save (enter 'none' if you don't want to save): ");
        saveFile = input.next();
        if (saveFile.equals("none")){
          return;
        }else{
          SaveToFile.save(game, saveFile, "assets");
        }
    }

}