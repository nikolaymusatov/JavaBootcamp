package ex02;

public class User {
  public User(String name, int balance) {
    this.id = UserIdsGenerator.getInstance().generateId();
    this.name = name;
    this.balance = balance;
  }

  public void printContent() {
    System.out.printf("ID: %d, name: %s, balance: %d\n", this.id, this.name, this.balance);
  }

  public int getId() {
    return this.id;
  }

  private int id;
  private String name;
  private int balance;
}