package game;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import tictactoe.TicTacToeView;
import tictactoe.NumTTTView;
import javax.swing.JFileChooser;
import java.io.File;
import player.Player;
import tictactoe.SaveToFile;
/**
This class represents the graphical view of the main window for the game suite. The class will create
a menu bar from which the user can load/save player profiles. The class creates two buttons that allow
the user to play one of the two games. Also shows player wins/losses.

@author Alessandro Arezza

*/
public class GameUI extends JFrame{
    private JPanel gameGrid;
    private JPanel playerPanel;
    private Player player1;
    private Player player2;
    private String file;
    private String[] path = new String[1];
    private JLabel firstPlayerWins = new JLabel();
    private JLabel secondPlayerWins = new JLabel();
    private JLabel firstPlayerLosses = new JLabel();
    private JLabel secondPlayerLosses = new JLabel();

    public GameUI(){
        super();
        player1 = new Player();
        player2 = new Player();
        setPreferredSize(new Dimension(318,300));
        setTitle("TicTacToe Suite");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setJMenuBar(createInitialPlayerMenu());
        playerPanel = new JPanel();
        gameGrid = new JPanel();
        add(gameGrid, BorderLayout.NORTH);
        add(makeGameButtons(),BorderLayout.CENTER);
        playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.Y_AXIS));
        add(playerPanel, BorderLayout.SOUTH);
        updatePlayerPanel();
        startUp();
    }

    private JPanel makeGameButtons(){
        JPanel border = new JPanel();
        JPanel buttons = new JPanel();
        border.setLayout(new BorderLayout());
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
        buttons.add(ticTacToeButton());
        buttons.add(numTTTButton());
        border.add(buttons);
        return border;
    }
    private JButton ticTacToeButton(){
        JButton gameButton = new JButton("Play Tic Tac Toe");
        gameButton.addActionListener(e -> makeTicTacToe());
        return gameButton;
    }

    private JButton numTTTButton(){
        JButton gameButton = new JButton("Play Number Tic Tac Toe");
        gameButton.addActionListener(e -> makeNumTTT());
        return gameButton;
    }

    private void makeTicTacToe(){
        gameGrid.removeAll();
        gameGrid.add(new TicTacToeView(this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
    private void makeNumTTT(){
        gameGrid.removeAll();
        gameGrid.add(new NumTTTView(this));
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }
    /**
    Restart the main window and remove all game elements. Show startup message.
     */
    public void startUp(){
        gameGrid.removeAll();
        gameGrid.add(new JLabel("Welcome to the Tic Tac Toe Game Suite"), BorderLayout.CENTER);
        updatePlayerPanel();
        getContentPane().repaint();
        getContentPane().revalidate();
        pack();
    }

    private JMenuBar createInitialPlayerMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Profile");
        menuBar.add(menu);
        
        JMenuItem savePlayer = new JMenuItem("Save player profile");
        JMenuItem loadPlayer = new JMenuItem("load player profile");
        
        savePlayer.addActionListener(e -> saveProfile());
        loadPlayer.addActionListener(e -> {
                                           loadProfile();
                                           updatePlayerPanel();});
        menu.add(savePlayer);
        menu.add(loadPlayer);
        return menuBar;
    }

    /**
    Update the player statistics display
    */
    public void updatePlayerPanel(){
        firstPlayerWins.setText("Player 1 Wins:" + player1.getWins());
        firstPlayerLosses.setText("Player 1 Losses:" + player1.getLosses());
        secondPlayerWins.setText("Player 2 Wins:" + player2.getWins());
        secondPlayerLosses.setText("Player 2 Losses:" + player2.getLosses());
        playerPanel.add(firstPlayerWins);
        playerPanel.add(firstPlayerLosses);
        playerPanel.add(secondPlayerWins);
        playerPanel.add(secondPlayerLosses);
    }

    /**
    Update the player statistics for the first player 
    @param player a player element
    */
    public void setPlayer1(Player player){
        for (int i = 0; i < player.getWins();i++){
            player1.addWin();
        }
        for (int i = 0; i < player.getLosses();i++){
            player1.addLoss();
        }
        for (int i = 0; i < player.getGamesPlayed();i++){
            player1.addGamePlayed();
        }
    }

    /**
    Update the player statistics for the second player 
    @param player a player element
    */
    public void setPlayer2(Player player){
        for (int i = 0; i < player.getWins();i++){
            player2.addWin();
        }
        for (int i = 0; i < player.getLosses();i++){
            player2.addLoss();
        }
        for (int i = 0; i < player.getGamesPlayed();i++){
            player2.addGamePlayed();
        }
    }
    private void loadProfile(){
        JFileChooser fileChooser = new JFileChooser();
        String playerNumber;
        playerNumber = JOptionPane.showInputDialog("Which player would you like to load (Enter 1 or 2):");
        try{
            verifyPlayerNumber(playerNumber);
            fileChooser.showSaveDialog(this);
            file = fileChooser.getSelectedFile().getName();
            path = fileChooser.getSelectedFile().getAbsolutePath().split(file); 
            if (playerNumber.equals("1")){
                SaveToFile.load(player1,file,path[0]);
            }else{
                SaveToFile.load(player2,file,path[0]);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    private void saveProfile(){
        JFileChooser fileChooser = new JFileChooser();
        String playerNumber;
        playerNumber = JOptionPane.showInputDialog("Which player would you like to save (Enter 1 or 2):");
        try{
            verifyPlayerNumber(playerNumber);
            fileChooser.showSaveDialog(this);
            file = fileChooser.getSelectedFile().getName();
            path = fileChooser.getSelectedFile().getAbsolutePath().split(file); 
            if (playerNumber.equals("1")){
                SaveToFile.save(player1,file + ".txt",path[0]);
            }else{
                SaveToFile.save(player2,file + ".txt",path[0]);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }
    private void verifyPlayerNumber(String playerNum) throws Exception{
        if (playerNum.equals("1") || playerNum.equals("2")){
            return;
        }else{
            throw new Exception("Please enter 1 or 2");
        }
    }
    public static void main(String[] args){
        GameUI application = new GameUI();
        application.setVisible(true);

    }


}
