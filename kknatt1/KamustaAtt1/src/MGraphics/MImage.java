package MGraphics;

import Exceptions.MImageCreationErrorException;
import Exceptions.MImageNotFound;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class MImage extends MGraphics {    
    protected Image image = null;
    
    public static void CreateMImage(String name, String filename, int height, int width, int pX, int pY, int layer) throws MImageCreationErrorException {
        AllImages.add(new MImage(name, pX, pY, layer, LoadImage(filename, height, width)));
    }
    
    protected MImage(String namee, int pXx, int pYy, int layerr, Image imagee) {
        ID_name = namee;
        ID_Hash = namee.hashCode();
        image = imagee;
        pX = pXx;
        pY = pYy;
        Layer = layerr;
        AllGraphics.add(this);
    }
    
    protected static Image LoadImage(String filename, int height, int width) throws MImageCreationErrorException {
        Image tempimg = null;
        try {
            tempimg = ImageIO.read(new File(filename)).getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH);
        } catch (Exception ex) {
            throw new MImageCreationErrorException("MImage.LoadImage()");
        }
        return (tempimg);
    }
    
    public static MImage FindFromName(String name) throws MImageNotFound {
        int hashie = name.hashCode();
        for (int i=0; i<AllImages.size(); i++) {
            if (AllImages.get(i).ID_Hash == hashie) {
                return AllImages.get(i);
            }
        }
        throw new MImageNotFound("MImage.FindFromName tried to find \""+name+"\"");
    }
    
    public static MImage FindFromHash(int hashie) throws MImageNotFound {
        for (int i=0; i<AllImages.size(); i++) {
            if (AllImages.get(i).ID_Hash == hashie) {
                return AllImages.get(i);
            }
        }
        throw new MImageNotFound("MImage.FindFromName tried to find \""+hashie+"\"");
    }
}
