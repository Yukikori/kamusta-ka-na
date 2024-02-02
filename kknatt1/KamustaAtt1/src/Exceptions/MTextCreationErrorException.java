package Exceptions;

import kamustaatt1.Main;

public class MTextCreationErrorException extends Exception {
    public MTextCreationErrorException(String root) {
        super("There was an error with creating an MText. This error was sent by: "+root);
        Main.ForceStopThrown("There was an error with creating an MText. This error was sent by: "+root);
    }
}
