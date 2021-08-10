package nl.molens;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MolenUtils {

  public static class Dimensions{

    private int height;
    private int width;
    private InputStream inputStream;

    public Dimensions(){}

    public Dimensions(int height, int width) {
      this.height = height;
      this.width = width;
    }
    public int getHeight() {
      return height;
    }

    public int getCorrectedHeight(){

      if (height!=0 && width!=0)
      {
        double ratio = (double)height/(double)width;
        if (width>350)
        {
          int corWidth=(int)Math.round(350*ratio);
          return corWidth;
        }
        else
        {
          int corWidth=(int)Math.round(width*ratio);
          return corWidth;
        }
      }
      return height;
    }

    public void setHeight(int height) {
      this.height = height;
    }
    public int getWidth() {
      return width;
    }

    public int getCorrectedWidth(){
      return width>350?350:width;
    }

    public void setWidth(int width) {
      this.width = width;
    }

    public InputStream getInputStream() {
      return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
      this.inputStream = inputStream;
    }

  }

  public static HttpSession session() {
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    return attr.getRequest().getSession(true); // true == allow create
}

public static Dimensions getImageDimensions(InputStream inputStream, String fileType)
{
  Dimensions dimensions = new Dimensions();
  String imageType = fileType.split("/")[1];
  try {
    BufferedImage bufferedImage = ImageIO.read(inputStream);
    dimensions.setHeight(bufferedImage.getHeight());
    dimensions.setWidth(bufferedImage.getWidth());
    ByteArrayOutputStream bArrayOutputstream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage,imageType,bArrayOutputstream);
    ByteArrayInputStream bArrayInputStream = new ByteArrayInputStream(bArrayOutputstream.toByteArray());
    dimensions.setInputStream(bArrayInputStream);
  } catch (IOException e) {
    return null;
  }
  return dimensions;

}

}
