package ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloader implements Runnable {
  private FilesQueue filesQueue;

  private Downloader(FilesQueue filesQueue) {
    this.filesQueue = filesQueue;
  }

  public static void download(FilesQueue filesQueue, int numberOfThreads) {
    for (int i = 0; i < numberOfThreads; i++) {
      Thread thread = new Thread(new Downloader(filesQueue));
      thread.start();
    }
  }

  public void run() {
    while (true) {
      String file = filesQueue.getFile();
      Path path = Paths.get(file);
      System.out.printf("%s is downloading file: %s\n", Thread.currentThread().getName(),
          path.getFileName().toString());
      try {
        Files.createDirectories(Paths.get("Downloads"));
        BufferedInputStream in = new BufferedInputStream(new URI(file).toURL().openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("Downloads/" + path.getFileName().toString());
        byte dataBuffer[] = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
          fileOutputStream.write(dataBuffer, 0, bytesRead);
        }
        fileOutputStream.close();
        in.close();
      } catch (Exception e) {
      }
      System.out.printf("%s has downloaded file: %s\n", Thread.currentThread().getName(),
          path.getFileName().toString());
    }
  }
}
