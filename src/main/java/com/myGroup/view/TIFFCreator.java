package com.myGroup.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class TIFFCreator {
    WritableImage bufferedImage = null;


    public TIFFCreator(String path) {
        ImageInputStream is;
        try {
            is = ImageIO.createImageInputStream(new File(path));  //read tiff using imageIO (JAI component)
            if (is == null || is.length() == 0) {
                System.out.println("Image is null");
            }

            Iterator<ImageReader> iterator = ImageIO.getImageReaders(is);
            if (iterator == null || !iterator.hasNext()) {
                throw new IOException("Image file format not supported by ImageIO: " + path);
            }
            ImageReader reader = (ImageReader) iterator.next();
            reader.setInput(is);
            int nbPages = reader.getNumImages(true);
            BufferedImage bf = reader.read(0);   //1st page of tiff file


            if (bf != null) {
                bufferedImage= SwingFXUtils.toFXImage(bf, null);   //convert bufferedImage (awt) into Writable Image(fx)
            }
        }

        catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WritableImage getBufferedImage() {
        return bufferedImage;
    }
}
