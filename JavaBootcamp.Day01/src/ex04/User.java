package ex04;

public class User {
  public User(String name, int balance) {
    this.id = UserIdsGenerator.getInstance().generateId();
    this.name = name;
    this.balance = balance;
    this.transactionsList = new TransactionsLinkedList();
  }

  public void printContent() {
    System.out.printf("ID: %d, name: %s, balance: %d\n", this.id, this.name, this.balance);
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

  private int id;
  private String name;
  private int balance;
  TransactionsList transactionsList;
}