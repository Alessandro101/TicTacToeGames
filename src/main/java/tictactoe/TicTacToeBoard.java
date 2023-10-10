package tictactoe;

/**
This class represents the board for tic tac toe. This class will define win conditions for the game
and define errors that can occur with input.

@author Alessandro Arezza

*/
public class TicTacToeBoard extends boardgame.Grid{

    public TicTacToeBoard(int wide, int tall){
        super(wide, tall);
    }

    /**
    Will check for one of the winning combinations
    @return status of a win
    */
    public boolean checkWin(){
        return checkHorizontalWin() || checkVerticalWin()|| checkDiagonalWin();
    }

    private boolean checkHorizontalWin(){
        for(int i = 1; i < 4; i++){
            if ((getValue(1,i).equals("X") || getValue(1,i).equals("O")) 
            && getValue(1,i).equals(getValue(2,i)) && getValue(2,i).equals(getValue(3,i))){
                return true;
            }
        }
        return false;
    }

    private boolean checkVerticalWin(){
        for(int i = 1; i < 4; i++){
            if ((getValue(i,1).equals("X") || getValue(i,1).equals("O")) 
            && getValue(i,1).equals(getValue(i,2)) && getValue(i,2).equals(getValue(i,3))){
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(){

        return (getValue(2,2).equals("X") || getValue(2,2).equals("O")) 
        && (getValue(1,1).equals(getValue(2,2)) && getValue(2,2).equals(getValue(3,3)) 
        || getValue(3,1).equals(getValue(2,2)) && getValue(2,2).equals(getValue(1,3)));
        
    }

    /**
    Will check for a full board 
    @return status of a tie
    */
    public boolean checkTie(){
        int numOfSymbols = 0;
        for (int i = 1; i < 4; i++){
            for (int j = 1; j < 4; j++){
            if(getValue(j,i).equals("X") || getValue(j,i).equals("O")){
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

}
