package ex01;

public class User {
  public User(String name, int balance) {
    this.id = UserIdsGenerator.getInstance().generateId();
    this.name = name;
    this.balance = balance;
  }

  public void printContent() {
    System.out.printf("ID: %d, name: %s, balance: %d\n", this.id, this.name, this.balance);
  }

  public int getBalance() {
    return this.balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  private int id;
  private String name;
  private int balance;
}