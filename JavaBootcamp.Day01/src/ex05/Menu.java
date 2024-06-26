package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
  public Menu() {
    this.transactionsService = new TransactionsService();
    this.scan = new Scanner(System.in);
  }

  public void show() {
    System.out.println("1. Add a user\n" + //
        "2. View user balances\n" + //
        "3. Perform a transfer\n" + //
        "4. View all transactions for a specific user\n" + //
        "5. Finish execution");
  }

  public void showDev() {
    System.out.println("1. Add a user\n" + //
        "2. View user balances\n" + //
        "3. Perform a transfer\n" + //
        "4. View all transactions for a specific user\n" + //
        "5. DEV – remove a transfer by ID\n" + //
        "6. DEV – check transfer validity\n" + //
        "7. Finish execution");
  }

  public void addUser() {
    System.out.println("Enter a user name and a balance");
    String data = this.scan.nextLine();
    String[] splited = data.split(" ");
    int id = this.transactionsService.addUser(new User(splited[0].strip(), Integer.parseInt(splited[1].strip())));
    System.out.printf("User with id = %d is added\n", id);
  }

  public void viewBalance() {
    while (true) {
      try {
        System.out.println("Enter a user ID");
        String data = this.scan.nextLine();
        System.out.printf("%s - %d\n", this.transactionsService.getUserById(Integer.parseInt(data.strip())).getName(),
            this.transactionsService.getUsersBalance(Integer.parseInt(data.strip())));
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void performTransfer() {
    while (true) {
      try {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String data = this.scan.nextLine();
        String[] splited = data.split(" ");
        this.transactionsService.addTransaction(Integer.parseInt(splited[0].strip()),
            Integer.parseInt(splited[1].strip()), Integer.parseInt(splited[2].strip()));
        System.out.println("The transfer is completed");
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void viewUsersTransactions() {
    while (true) {
      try {
        System.out.println("Enter a user ID");
        String data = this.scan.nextLine();
        Transaction[] transactions = this.transactionsService.getUsersTransactions(Integer.parseInt(data.strip()));
        for (Transaction t : transactions) {
          if (t.getCategory() == Transaction.TransferCategory.OUTCOME)
            System.out.printf("To %s(id = %d) %d with id = %s\n", t.getRecipient().getName(), t.getRecipient().getId(),
                t.getAmount(), t.getId().toString());
          else
            System.out.printf("From %s(id = %d) %d with id = %s\n", t.getSender().getName(), t.getSender().getId(),
                t.getAmount(), t.getId().toString());
        }
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void removeTransaction() {
    while (true) {
      try {
        System.out.println("Enter a user ID and transaction ID");
        String[] data = this.scan.nextLine().split(" ");
        Transaction removed = this.transactionsService.removeTransaction(Integer.parseInt(data[0].strip()),
            UUID.fromString(data[1].strip()));
        System.out.printf("Transfer to %s(id = %d) %d removed\n", removed.getRecipient(),
            removed.getRecipient().getId(),
            removed.getAmount());
        break;
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  public void checkTransactionsValidity() {
    Transaction[] unvalid = this.transactionsService.checkTransactionsValidity();
    for (Transaction t : unvalid) {
      System.out.printf(
          "%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
          t.getRecipient().getName(), t.getRecipient().getId(), t.getId().toString(), t.getSender().getName(),
          t.getSender().getId(), t.getAmount());
    }
  }

  public void chooseAction() {
    String action = this.scan.nextLine();
    switch (action) {
      case "1":
        this.addUser();
        break;
      case "2":
        this.viewBalance();
        break;
      case "3":
        this.performTransfer();
        break;
      case "4":
        this.viewUsersTransactions();
        ;
        break;
      case "5":
        this.finishExecution();
        break;
      default:
        System.out.println("Wrong action!");
        break;
    }
  }

  public void chooseActionDev() {
    String action = this.scan.nextLine();
    switch (action) {
      case "1":
        this.addUser();
        break;
      case "2":
        this.viewBalance();
        break;
      case "3":
        this.performTransfer();
        break;
      case "4":
        this.viewUsersTransactions();
        break;
      case "5":
        this.removeTransaction();
        break;
      case "6":
        this.checkTransactionsValidity();
        break;
      case "7":
        this.finishExecution();
        break;
      default:
        System.out.println("Wrong action!");
        break;
    }
  }

  public void finishExecution() {
    this.scan.close();
    System.exit(0);
  }

  private TransactionsService transactionsService;
  private Scanner scan;
}
