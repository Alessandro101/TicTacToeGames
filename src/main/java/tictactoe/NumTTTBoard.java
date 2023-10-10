package tictactoe;

/**
This class represents the board for the numerical tic tac toe. This class will define win conditions for the game
and define errors that can occur with input.

@author Alessandro Arezza

*/
public class NumTTTBoard extends boardgame.Grid{

    public NumTTTBoard(int wide, int tall){
        super(wide, tall);
    }

    /**
    Will check for one of the winning combinations
    @return status of a win
    */
    public boolean checkWin(){
        return checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin();
    }

    private boolean checkHorizontalWin(){
        int horizontalSum = 0;
        boolean win = false;
        int nums;
        for(int i = 1; i < 4; i++){
            horizontalSum = 0; 
            nums = 0;
            for (int j = 1; j < 4; j++){
            if (getValue(j,i).equals(" ")){
                horizontalSum = horizontalSum;
            }else{
                horizontalSum = horizontalSum + Integer.valueOf(getValue(j,i));
                nums++;
            }
                if (nums == 3 && horizontalSum == 15){
                    win = true;
                    return win;
                }
            }
        }
        return win;
    }

    private boolean checkVerticalWin(){
        int verticalSum = 0;
        boolean win = false;
        int nums;
        for(int i = 1; i < 4; i++){
            verticalSum = 0;
            nums = 0;
            for (int j = 1; j < 4; j++){
            if (getValue(i,j).equals(" ")){
                verticalSum = verticalSum;
            }else{
                verticalSum = verticalSum + Integer.valueOf(getValue(i,j));
                nums++;
            }
            if (nums == 3 && verticalSum == 15){
                win = true;
                return win;
            }
            }
        }
        return win;
    }
    private boolean checkDiagonalWin(){
        int diagonalWin = 0;
        int k = 1;
        int nums = 0;
        for (int i = 1; i < 4; i++){
            if(getValue(i,i).equals(" ")){
                diagonalWin = diagonalWin;
            }else{
                diagonalWin = diagonalWin + Integer.valueOf(getValue(i,i));
                nums++;
            }
        }
        if (nums == 3 && diagonalWin == 15){
            return true;
        }else{
            diagonalWin = 0;
            nums = 0;
            for (int j = 3; j > 0; j--){
              if (getValue(j,k).equals(" ")){
                diagonalWin = diagonalWin;
              }else{
                diagonalWin = diagonalWin + Integer.valueOf(getValue(j,k));
                nums++;
              }
              k++;
            }
        return (nums == 3 && diagonalWin == 15);
        }
    }

    /**
    Will check for a full board 
    @return status of a tie
    */
    public boolean checkTie(){
        int numOfSymbols = 0;
        for (int i = 1; i < 4; i++){
            for (int j = 1; j < 4; j++){
            if(getValue(j,i).equals(" ")){
                numOfSymbols = numOfSymbols;
            }else{
                numOfSymbols++;
            }
            }
        }
        return (numOfSymbols == 9);
    }

    /**
    Will check for invalid locations when performing input operations
    @param across coloumn
    @param down row
    */
    public void checkValidLocation(int across, int down) throws Exception{
        if (getValue(across, down).equals(" ") && (across >=1 && across <=3 && down >=1 && down <=3)){
            return;
        }else{
            throw new Exception("Invalid location"); 
        }
    }

    /**
   Will check for invalid user inputs when performing input operations
    @param input value entered by the user
    @param player the current player 
    */
    public void checkValidInput(String input, int player) throws Exception{
        if (Integer.valueOf(input) < 0 || Integer.valueOf(input) > 9){
            throw new Exception("Number is out of range");
        }
        if (player == 1 && (Integer.valueOf(input) % 2 == 0)){
            throw new Exception("Please enter an odd number (1,3,5,7,9)");
        }
        if (player == 2 && (Integer.valueOf(input) % 2 == 1)){
            throw new Exception("Please enter an even number (0,2,4,6,8)");
        }
        for (int i = 1; i < 4; i++){
            for (int j = 1; j <4; j++){
              if (input.equals(getValue(j,i))){
                throw new Exception("That number has already been used");
              }
            }
        }
    }
}
