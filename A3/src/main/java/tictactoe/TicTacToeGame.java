package tictactoe;

/**
This class represents the tic tac toe game. This class will allow the user to change the
board of the game, check for valid input, check for win conditions, and provide the 
saving/loading of .csv game files.

@author Alessandro Arezza

*/
public class TicTacToeGame extends boardgame.BoardGame implements boardgame.Saveable{
    private int currentPlayer;
    private String gameStateMessage;
    private int exception; 
    private String exceptionMessage;
    private int tie;

    public TicTacToeGame(int wide, int tall){
        super(wide,tall);
        setGrid(new TicTacToeBoard(wide, tall));
        currentPlayer = 1;
        tie = 0;
        exception = 0;
        playerMessage();
    }


    /**
    Allows the user to set a value in the grid in a certain coordinate using a string.
    Checks for valid input and location.
    @param across coloumn
    @param down row
    @param input symbol
    @return false
    */
    @Override
    public boolean takeTurn(int across, int down, String input){

        try{
            grid().checkValidLocation(across,down);
            setValue(across, down, input);
            if (!isDone()){
              switchCurrentPlayer();
            } 
        }catch(Exception e){
            setExceptionMessage(e);
            exception = 1;

        }

        return false;
    }
    
    @Override
    public boolean takeTurn(int across, int down, int input){
        return false;
    }

    /**
    Checks for win/tie conditions, and changes gameStateMessage for end of game
    @return status of win/tie
    */
    @Override
    public boolean isDone(){
        exception = 0;
        if (grid().checkWin()){
            tie = 0;
            endGameMessage();
            return true;
        }else if(grid().checkTie()){
            endGameMessage();
            tie = 1;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getGameStateMessage(){
        return gameStateMessage;
    }


    /**
    Return a value depending on if player 1 won, player 2 won, or a tie
    @return 1 if player 1 wins, 2 if player 2 wins, 0 if tie, -1 if nothing
    */
    @Override
    public int getWinner(){
        if (currentPlayer == 1 && tie != 1){
            return 1;
        }else if (currentPlayer == 2 && tie != 1){
            return 2;
        }else if (tie == 1){
            return 0;
        }
        return -1;
    }

    /**
    Return the string representation of the grid
    */
    @Override
    public String getStringToSave(){
        StringBuilder stringToSave = new StringBuilder("");
        if (currentPlayer == 1){
            stringToSave.append("O\n");
        }else{
            stringToSave.append("X\n");
        }
        for (int i = 1; i < 4; i++){
            for (int j = 1; j < 4; j++){
            if (getCell(j,i).equals(" ")){
                stringToSave.append("");
            }else{
                stringToSave.append(getCell(j,i));
            }
            if (j != 3){
                stringToSave.append(",");
            }
        }
            if(i != 3){
              stringToSave.append("\n");
            }
        }
        return stringToSave.toString();
    }

    /**
    Parse a csv file and add contents to the grid of the game
    @param toLoad String to be parsed
    */
    @Override
    public void loadSavedString(String toLoad){
      StringBuilder elementList = new StringBuilder("");
      String[] lines = toLoad.split("\n");
      int c = 0;

      for (int i = 1; i < 4; i++){
        for (int j = 0; j < lines[i].length();j++){
          if (j == 0 || j == lines[i].length()-1){
            if (lines[i].charAt(j) == ','){
              elementList.append(" ");
          }
        }
          if (j != lines[i].length()-1){
            if (lines[i].charAt(j) == ',' && lines[i].charAt(j+1) == ','){
              elementList.append(" ");
            }
          }
          if (lines[i].charAt(j) != ','){
            elementList.append(lines[i].charAt(j));
          }
        }
      }
      loadNewPlayer(lines[0]);
      for (int k = 1; k < 4; k++){
        setValue(1,k,String.valueOf(elementList.charAt(c)));
        setValue(2,k,String.valueOf(elementList.charAt(c+1)));
        setValue(3,k,String.valueOf(elementList.charAt(c+2)));
        c = c + 3;
      }
    }
    private TicTacToeBoard grid(){
        return (TicTacToeBoard) getGrid();
    }
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    private void playerMessage(){
        if (currentPlayer == 1){
            setGameStateMessage("Player 1 is about to move");
        }else{
            setGameStateMessage("Player 2 is about to move");
        }
    }
    private void switchCurrentPlayer(){
        if (currentPlayer == 1){
            currentPlayer = 2;
        }else{
            currentPlayer = 1;
        }
        playerMessage();
    }
    public void setGameStateMessage(String message){
        gameStateMessage = message;
    }
    private void endGameMessage(){
        if (grid().checkWin()){
            setGameStateMessage("Winner is Player " + currentPlayer);
        }else{
            setGameStateMessage("The game is a tie!");
        }
    }
    public void resetConditions(){
        currentPlayer = 1;
        playerMessage();
    }
    public void loadNewPlayer(String line){
      if (line.charAt(0) == 'X'){
        currentPlayer = 2;
      }else{
        currentPlayer = 1;
      }
      playerMessage();
    }
    private void setExceptionMessage(Exception e){
        exceptionMessage = e.getMessage();
    }
    public String getExceptionMessage(){
        return exceptionMessage;
    }

    public int getExceptionStatus(){
        return exception;
    }
}
