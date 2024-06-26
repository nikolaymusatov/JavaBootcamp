package ex00;

import java.util.UUID;

public class Transaction {
  public enum TransferCategory {
    INCOME,
    OUTCOME
  }

  public Transaction(User sender, User recipient, TransferCategory category, int amount) {
    this.identifier = UUID.randomUUID();
    this.sender = sender;
    this.recipient = recipient;
    this.transferCategory = category;
    this.transferAmount = amount;
  }

  public void printContent() {
    System.out.printf("ID: %s, senders ID: %s, recipients ID: %s, category: %s, amount: %d\n",
        this.identifier.toString(),
        this.sender.getId(),
        this.recipient.getId(), this.transferCategory, this.transferAmount);
  }

  private UUID identifier;
  private User sender;
  private User recipient;
  private TransferCategory transferCategory;
  private int transferAmount;
}
