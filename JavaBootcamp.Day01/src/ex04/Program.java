package ex04;

public class Program {
  public static void main(String[] args) {
    User Mike = new User("Mike", 1000);
    User Sam = new User("Sam", 500);
    TransactionsService transactionsService = new TransactionsService();
    transactionsService.addUser(Mike);
    transactionsService.addUser(Sam);

    System.out.println("Mike's balance before transaction: " + transactionsService.getUsersBalance(Mike.getId()));
    System.out.println("Sam's balance before transaction: " + transactionsService.getUsersBalance(Sam.getId()));
    transactionsService.addTransaction(Mike.getId(), Sam.getId(), 300);
    System.out.println("Mike's balance after transaction: " + transactionsService.getUsersBalance(Mike.getId()));
    System.out.println("Sam's balance after transaction: " + transactionsService.getUsersBalance(Sam.getId()));

    Transaction[] MikesTransactions = transactionsService.getUsersTransactions(Mike.getId());
    System.out.println("List of Mike's transactions:");
    for (Transaction t : MikesTransactions) {
      t.printContent();
    }

    Transaction[] SamsTransactions = transactionsService.getUsersTransactions(Sam.getId());
    System.out.println("List of Sam's transactions:");
    for (Transaction t : SamsTransactions) {
      t.printContent();
    }

    System.out.println("Removing Mike's transaction.");
    transactionsService.removeTransaction(Mike.getId(), MikesTransactions[0].getId());

    Transaction[] unpaired = transactionsService.checkTransactionsValidity();
    System.out.println("Unpaired transactions: ");
    for (Transaction t : unpaired) {
      t.printContent();
    }
  }
}
