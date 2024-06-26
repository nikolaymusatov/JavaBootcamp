package ex00;

public class User {
  public User(int id, String name, int balance) {
    this.id = id;
    this.name = name;
    this.balance = balance;
  }

  public int getId() {
    return this.id;
  }

  public int getBalance() {
    return this.balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public void printContent() {
    System.out.printf("ID: %d, name: %s, balance: %d\n", this.id, this.name, this.balance);
  }

  private int id;
  private String name;
  private int balance;
}