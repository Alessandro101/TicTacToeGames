package tictactoe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import boardgame.ui.PositionAwareButton;
import game.GameUI;
import javax.swing.JFileChooser;
import java.io.File;
import player.Player;

/**
This class represents the graphical view of the Numerical tic tac toe game. The class will generate a button
grid for the user to interact with, call methods from the numTTT game to check win and take turns. 
The class also creates a menu from which the player can save/load games from a csv file.

@author Alessandro Arezza

*/
public class NumTTTView extends JPanel{
    private PositionAwareButton[][] buttons = new PositionAwareButton[3][3];
    private NumTTTGame game;
    private GameUI root;
    private JLabel message = new JLabel();
    private String file;
    private String directory;
    private int loadedStatus = 0;
    private JMenu saveLoadMenu;
    private String[] path = new String[1];
    private Player player1;
    private Player player2;

    public NumTTTView(GameUI main){
        super();
        player1 = new Player();
        player2 = new Player();
        setLayout(new BorderLayout());
        game = new NumTTTGame(3,3);
        root = main;
        add(new JLabel("Welcome to Numerical Tic Tac Toe!"),BorderLayout.NORTH);
        add(makeGrid(),BorderLayout.CENTER);
        add(createInitialSaveLoadMenu(), BorderLayout.WEST);
        updateButtonGrid();
        displayGameStateMessage();

    }
    private JPanel makeGrid(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j] = new PositionAwareButton();
                buttons[i][j].setAcross(j+1);
                buttons[i][j].setDown(i+1);
                buttons[i][j].addActionListener(e -> {
                                                      inputNumber(game.getCurrentPlayer(),e);
                                                      checkGameState();});
                panel.add(buttons[i][j]);
            }
        }
        return panel;
    }

    private void inputNumber(int currentPlayer, ActionEvent e){
        updateButtonGrid();
        PositionAwareButton clickedButton = ((PositionAwareButton)(e.getSource()));
        String num;
        if (currentPlayer == 1){
            num = JOptionPane.showInputDialog("Enter an odd number (1,3,5,7,9):");
        }else{
            num = JOptionPane.showInputDialog("Enter an even number (0,2,4,6,8):");
        }
        game.takeTurn(clickedButton.getAcross(),clickedButton.getDown(),num);
        clickedButton.setText(game.getCell(clickedButton.getAcross(),clickedButton.getDown()));
        displayGameStateMessage();
        
        if (game.getExceptionStatus() == 1){
            JOptionPane.showMessageDialog(null,game.getExceptionMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }
    private void checkGameState(){
        int choice = 0;
        JOptionPane newGameOption = new JOptionPane();
        if (game.isDone()){
            updatePlayerStats();
            if (newGameOption.showConfirmDialog(null,"Would you like to start a new game?",
            "NewGame",JOptionPane.YES_NO_OPTION)  == JOptionPane.YES_OPTION){
                game.newGame();
                game.resetConditions();
                updateButtonGrid();
                displayGameStateMessage();
                root.updatePlayerPanel();
                player1.resetPlayer();
                player2.resetPlayer();
            }else{
                root.startUp();
            }
        }
    }
    private void displayGameStateMessage(){
        message.setText(game.getGameStateMessage());
        add(message,BorderLayout.SOUTH);
    }
    private void updateButtonGrid(){

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText(game.getCell(buttons[i][j].getAcross(),buttons[i][j].getDown()));
            }
        }
    
    }

    private JMenuBar createInitialSaveLoadMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Save/Load Game");
        menuBar.add(menu);
        JMenuItem saveGame = new JMenuItem("Save current game");
        
        JMenuItem loadGame = new JMenuItem("Load new game");
       
        saveGame.addActionListener(e -> saveGrid());
        loadGame.addActionListener(e -> {
                                        loadGrid();
                                        updateButtonGrid();
                                        displayGameStateMessage();
                                        });
        menu.add(saveGame);
        menu.add(loadGame);

        return menuBar;
    }

    private void loadGrid(){
        JFileChooser fileChooser = new JFileChooser();
      
        fileChooser.showOpenDialog(this);
        file = fileChooser.getSelectedFile().getName();
        path = fileChooser.getSelectedFile().getAbsolutePath().split(file);
        SaveToFile.load(game,file,path[0]);
    }
    
    private void saveGrid(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(this);
        file = fileChooser.getSelectedFile().getName();
        path = fileChooser.getSelectedFile().getAbsolutePath().split(file); 
        SaveToFile.save(game,file + ".csv",path[0]);
    }

    private void updatePlayerStats(){
        if(game.getWinner() == 1){
            player1.addWin();
            player2.addLoss();
            player1.addGamePlayed();
            player2.addGamePlayed();
        }else if (game.getWinner() == 2){
            player2.addWin();
            player1.addLoss();
            player1.addGamePlayed();
            player2.addGamePlayed();
        }else{
            player1.addGamePlayed();
            player2.addGamePlayed();
        }
        root.setPlayer1(player1);
        root.setPlayer2(player2);
    }

}