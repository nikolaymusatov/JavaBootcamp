package ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
  public static void readFile(FilesQueue urls, String path) throws IOException {
    FileReader fr = new FileReader(path);
    BufferedReader br = new BufferedReader(fr);
    String line = null;
    while ((line = br.readLine()) != null)
      urls.addFile(line);
    br.close();
    fr.close();
  }
}
