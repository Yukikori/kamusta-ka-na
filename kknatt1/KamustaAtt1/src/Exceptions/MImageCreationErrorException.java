package Exceptions;

import kamustaatt1.Main;

public class MImageCreationErrorException extends Exception {
    public MImageCreationErrorException(String root) {
        super("There was an error with creating an MImage. This error was sent by: "+root);
        Main.ForceStopThrown("There was an error with creating an MImage. This error was sent by: "+root);
    }
}
