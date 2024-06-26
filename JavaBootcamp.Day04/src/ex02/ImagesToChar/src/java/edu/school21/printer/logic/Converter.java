package ImagesToChar.src.java.edu.school21.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;
import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;

public class Converter {
  public static void convert(File file, String blankColor, String filledColor) throws Exception {
    int[] bColorRGB = getColor(blankColor);
    int[] fColorRGB = getColor(filledColor);
    Attribute bColor = BACK_COLOR(bColorRGB[0], bColorRGB[1], bColorRGB[2]);
    Attribute fColor = BACK_COLOR(fColorRGB[0], fColorRGB[1], fColorRGB[2]);
    BufferedImage image = ImageIO.read(file);
    for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
      for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
        int color = image.getRGB(xPixel, yPixel);
        if (color == Color.BLACK.getRGB())
          System.out.print(colorize(" ", fColor));
        else
          System.out.print(colorize(" ", bColor));
      }
      System.out.println();
    }
  }

  public static int[] getColor(String color) throws RuntimeErrorException {
    Map<String, int[]> colors = Map.of(
        "WHITE", new int[] { 255, 255, 255 },
        "BLACK", new int[] { 0, 0, 0 },
        "BLUE", new int[] { 0, 0, 255 },
        "RED", new int[] { 255, 0, 0 },
        "GREEN", new int[] { 0, 255, 0 },
        "PINK", new int[] { 255, 0, 255 },
        "YELLOW", new int[] { 255, 255, 0 });
    if (colors.get(color) == null)
      throw new RuntimeErrorException(null, "No such color: " + color);
    return colors.get(color);
  }
}
