package Exceptions;

import kamustaatt1.Main;

public class SaveFolderNotFoundException extends Exception {    
    public SaveFolderNotFoundException(String root) {
        super("There was an error with locating the saves folder. This error was sent by: "+root);
        Main.ForceStopThrown("There was an error with locating the saves folder. This error was sent by: "+root);
    }
}
