package MGraphics;

import Exceptions.MImageCreationErrorException;
import Exceptions.MImageNotFound;
import static MGraphics.MGraphics.AllGraphics;
import static MGraphics.MGraphics.AllImages;
import static MGraphics.MImage.LoadImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MAniImage extends MGraphics {
    protected ArrayList<Image> frames;
    protected int frame_index;
    
    public static void CreateMAniImage(String name, ArrayList<String> Image_filenames, int frame_indexx, int height, int width, int pX, int pY, int layer) throws MImageCreationErrorException, MImageNotFound {
        ArrayList<Image> framess = new ArrayList<>();
        
        for (int a=0; a<Image_filenames.size(); a++) {
            try {
                framess.add(ImageIO.read(new File(Image_filenames.get(a))).getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH));
            } catch (IOException ex) {
                Logger.getLogger(MAniImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        AllAniImages.add(new MAniImage(name, pX, pY, layer, framess, frame_indexx));
    }
    
    protected MAniImage(String namee, int pXx, int pYy, int layerr, ArrayList<Image> framess, int frame_indexx) {
        ID_name = namee;
        ID_Hash = namee.hashCode();
        frames = framess;
        frame_index = frame_indexx;
        pX = pXx;
        pY = pYy;
        Layer = layerr;
        AllGraphics.add(this);
    }
    
    public Image GetFrame(int frame_indexx) {
        return frames.get(frame_indexx);
    }
    
    public Image GetCurrFrame() {
        return frames.get(frame_index);
    }
    
    public int GetMaxFrame() {
        return frames.size()-1;
    }
    
    public void SetFrameIndex(int val) {
        frame_index = val;
    }
    
    public static MAniImage FindFromName(String name) throws MImageNotFound {
        int hashie = name.hashCode();
        for (int i=0; i<AllAniImages.size(); i++) {
            if (AllAniImages.get(i).ID_Hash == hashie) {
                return AllAniImages.get(i);
            }
        }
        throw new MImageNotFound("MImage.FindFromName tried to find \""+name+"\"");
    }
    
    public static MAniImage FindFromHash(int hashie) throws MImageNotFound {
        for (int i=0; i<AllAniImages.size(); i++) {
            if (AllAniImages.get(i).ID_Hash == hashie) {
                return AllAniImages.get(i);
            }
        }
        throw new MImageNotFound("MImage.FindFromName tried to find \""+hashie+"\"");
    }
}
