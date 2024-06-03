package Utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
    public static BufferedImage GetSpriteAtlas(String path){
        BufferedImage img;
        InputStream is = LoadSave.class.getResourceAsStream(path);
        try{
            img = ImageIO.read(is);
            return img;
        }catch(IOException e){

        }finally{
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

}
