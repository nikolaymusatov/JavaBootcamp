package ex03;

import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    try {
      if (args.length != 1)
        throw new RuntimeErrorException(null, "Wrong argument");
      int numberOfThreads = Integer.parseInt(args[0]);
      FilesQueue filesQueue = new FilesQueue();
      Reader.readFile(filesQueue, "files_urls.txt");
      Downloader.download(filesQueue, numberOfThreads);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
