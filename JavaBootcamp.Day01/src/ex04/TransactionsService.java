package ex04;

import java.util.UUID;

public class TransactionsService {
  public TransactionsService() {
    this.usersList = new UsersArrayList();
  }

  public void addUser(User user) {
    this.usersList.add(user);
  }

  public int getUsersBalance(int usersId) {
    return this.usersList.getUserById(usersId).getBalance();
  }

  public void addTransaction(int sendersId, int recipientsId, int amount) {
    UUID transactionId = UUID.randomUUID();
    User sender = this.usersList.getUserById(sendersId);
    User recipient = this.usersList.getUserById(recipientsId);
    Transaction debit = new Transaction(transactionId, sender, recipient, Transaction.TransferCategory.INCOME,
        amount);
    Transaction credit = new Transaction(transactionId, recipient, sender, Transaction.TransferCategory.OUTCOME,
        -amount);
    recipient.transactionsList.add(debit);
    sender.transactionsList.add(credit);
    sender.setBalance(sender.getBalance() - amount);
    recipient.setBalance(recipient.getBalance() + amount);
  }

  public Transaction[] getUsersTransactions(int usersId) {
    return this.usersList.getUserById(usersId).transactionsList.toArray();
  }

  public void removeTransaction(int usersId, UUID transactionsId) {
    this.usersList.getUserById(usersId).transactionsList.remove(transactionsId);
  }

  public Transaction[] checkTransactionsValidity() {
    TransactionsList unpairedTransactionsList = new TransactionsLinkedList();
    int usersNumber = this.usersList.getUsersNumber();
    for (int i = 0; i < usersNumber; i++) {
      Transaction[] transactions = usersList.getUserByIndex(i).transactionsList.toArray();
      for (Transaction t : transactions) {
        UUID id = t.getId();
        User sender = t.getSender();
        User recipient = t.getRecipient();
        if (!sender.transactionsList.contains(id) || !recipient.transactionsList.contains(id))
          unpairedTransactionsList.add(t);
      }
    }
    return unpairedTransactionsList.toArray();
  }

  private UsersList usersList;
}
