package player;
/**
This class represents a player in the game suite. This class will hold the number
of wins, losses and games played of each player in the game.

@author Alessandro Arezza

*/
public class Player implements boardgame.Saveable{
    private int wins = 0;
    private int losses = 0;
    private int gamesPlayed = 0;
    private int player;

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

    public int getGamesPlayed(){
        return gamesPlayed;
    }

    public void addWin(){
        wins++;
    }

    public void addLoss(){
        losses++;
    }

    public void addGamePlayed(){
        gamesPlayed++;
    }

    /**
    Reset all values of the player to 0
    */
    public void resetPlayer(){
        wins = 0;
        losses = 0;
        gamesPlayed = 0;
    }

    /**
    Holds the string that will be saved to a text file 
    */
    @Override
    public String getStringToSave(){
        return "Wins: " + wins + " Losses: " + losses + " Games Played: " + gamesPlayed;
    }
    /** 
    Parses a text file and fills in the contents of the player object
    */
    @Override 
    public void loadSavedString(String toLoad){
        String[] items = toLoad.split(" ");
        int[] stats = new int[3];
        int counter = 0;
        for (int i = 0; i < items.length; i++){
            try{
                stats[counter] = Integer.valueOf(items[i]);
                counter++;
            }catch(Exception e){
                //
            }
        }
        wins = stats[0];
        losses = stats[1];
        gamesPlayed = stats[2];
    }

}
