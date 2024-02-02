package Exceptions;

import kamustaatt1.Main;

public class GameStartUpUnsuccessfulException extends Exception{
    public GameStartUpUnsuccessfulException(String root) {
        super("There was an error with starting the game. This error was sent by: "+root);
        Main.ForceStopThrown("There was an error with starting the game. This error was sent by: "+root);
    }
}
