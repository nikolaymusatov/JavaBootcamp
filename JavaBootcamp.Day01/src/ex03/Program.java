package ex03;

import java.util.UUID;

import ex03.Transaction.TransferCategory;

public class Program {
  public static void main(String[] args) {
    User John = new User("John", 1000);
    User Mike = new User("Mike", 1000);
    Transaction trans1 = new Transaction(John, Mike, TransferCategory.OUTCOME,
        -500);
    Transaction trans2 = new Transaction(Mike, John, TransferCategory.INCOME,
        500);
    Transaction trans3 = new Transaction(John, Mike, TransferCategory.OUTCOME,
        -100);
    Transaction trans4 = new Transaction(Mike, John, TransferCategory.INCOME,
        100);
    John.transactionsList.add(trans1);
    Mike.transactionsList.add(trans2);
    John.transactionsList.add(trans3);
    Mike.transactionsList.add(trans4);
    System.out.println("John's transactions before removing:");
    John.transactionsList.printContent();
    John.transactionsList.remove(trans1.getId());
    System.out.println("John's transactions after removing:");
    John.transactionsList.printContent();
    System.out.print("Removing transaction with wrong id: ");
    try {
      John.transactionsList.remove(UUID.fromString("12345"));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Mike's transactions from array:");
    Transaction[] transactions = Mike.transactionsList.toArray();
    for (Transaction t : transactions) {
      t.printContent();
    }
  }
}
