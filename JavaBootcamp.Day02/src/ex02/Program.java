package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class Program {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Wrong argument");
      System.exit(-1);
    }
    Scanner scan = new Scanner(System.in);
    Path path = Paths.get(args[0]);
    System.out.printf("> %s ", path.getFileName() == null ? "/" : path.getFileName());
    String action = scan.nextLine();
    while (!action.equals("exit")) {
      try {
        if (action.startsWith("ls"))
          ls(path);
        else if (action.startsWith("cd"))
          path = cd(path, action);
        else if (action.startsWith("mv"))
          mv(path, action);
        else
          System.out.println("Unknown action");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      System.out.printf("> %s ", path.getFileName() == null ? "/" : path.getFileName());
      action = scan.nextLine();
    }
    scan.close();
  }

  public static void ls(Path path) throws IOException {
    File file = new File(path.toUri());
    for (File f : file.listFiles()) {
      if (f.isHidden())
        continue;
      else if (f.isFile())
        System.out.printf("%-15s %-10s\n", f.getName(), countSize(f.length()));
      else if (f.isDirectory())
        System.out.printf("%-15s %-10s\n", f.getName(),
            countSize(Files.walk(f.toPath()).map(Path::toFile).filter(File::isFile)
                .mapToLong(File::length).sum()));
    }
  }

  public static String countSize(long bytes) {
    String[] suffixes = { " B", " KB", " MB", " GB", " TB" };
    int index = 0;
    while (bytes >= 1024) {
      bytes /= 1024;
      index++;
    }
    return Long.toString(bytes) + suffixes[index];
  }

  public static Path cd(Path currentPath, String action) {
    String[] splittedAction = action.split(" ");
    if (splittedAction.length != 2)
      throw new RuntimeErrorException(null, "Wrong argument");
    Path newPath = Paths.get(currentPath.toString() + "/" + splittedAction[1]);
    if (!Files.exists(newPath))
      throw new RuntimeErrorException(null, "No such directory: " + splittedAction[1]);
    return newPath.toAbsolutePath().normalize();
  }

  public static void mv(Path path, String action) throws IOException {
    String[] splittedAction = action.split(" ");
    if (splittedAction.length != 3)
      throw new RuntimeErrorException(null, "Wrong argument");
    Path filePath = Paths.get(path.toString() + "/" + splittedAction[1]).toAbsolutePath().normalize();
    Path newPath = null;
    Path fileName = filePath.getFileName();
    if (Files.isDirectory(Paths.get(splittedAction[2]))) {
      newPath = Paths.get(path.toString() + "/" + splittedAction[2] + "/" + fileName.toString()).toAbsolutePath()
          .normalize();
    } else {
      newPath = Paths.get(path.toString() + "/" + splittedAction[2]).toAbsolutePath().normalize();
    }
    Files.move(filePath, newPath);
  }
}
