package tictactoe;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import boardgame.Saveable;

/**
This class represents the static methods used for saving and loading files throughout the program.

@author Alessandro Arezza

*/
public class SaveToFile{
    public static void save(Saveable save, String file, String s){
        Path path = FileSystems.getDefault().getPath(s, file);
        try{
            Files.writeString(path,save.getStringToSave());
        }catch(IOException e){
            throw (new RuntimeException(e.getMessage()));
        }
    }

    public static void load(Saveable load, String file, String s){
        Path path = FileSystems.getDefault().getPath(s, file);
        try{
        String fileContents = String.join("\n", Files.readAllLines(path));
        load.loadSavedString(fileContents);
        }catch(IOException e){
            throw (new RuntimeException(e.getMessage()));
        }
    }
}