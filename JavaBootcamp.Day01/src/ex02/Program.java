package ex02;

public class Program {
  public static void main(String[] args) {
    UsersList usersList = new UsersArrayList();
    usersList.addUser(new User("Mike", 1000));
    usersList.addUser(new User("John", 1100));
    usersList.addUser(new User("Sally", 600));
    usersList.addUser(new User("Ann", 1900));
    usersList.addUser(new User("Bob", 1450));
    usersList.printContent();
    System.out.println("get user by id = 2");
    usersList.getUserById(2).printContent();
    System.out.println("get user by id = 12");
    try {
      usersList.getUserById(12).printContent();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    System.out.println("get user by index = 2");
    usersList.getUserByIndex(2).printContent();
    System.out.println("get user by index = 12");
    try {
      usersList.getUserByIndex(12).printContent();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Number of users: " + usersList.getUsersNumber());
  }
}
