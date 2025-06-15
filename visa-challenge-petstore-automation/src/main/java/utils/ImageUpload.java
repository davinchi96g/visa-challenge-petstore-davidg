package utils;
import java.io.File;


public class ImageUpload {

    public static File getImage(String file) {
        File image = new File(Constants.IMAGE_PATH + file);
        if (!image.exists()) {
            throw new RuntimeException("Image not found " + image.getAbsolutePath());
        }
        return image;
    }
}
