package ex04;

public class UserIdsGenerator {
  public static UserIdsGenerator getInstance() {
    if (instance == null)
      instance = new UserIdsGenerator();
    return instance;
  }

  public int generateId() {
    currentId++;
    return currentId;
  }

  private static UserIdsGenerator instance;

  private static int currentId;

  private UserIdsGenerator() {
  }
}
