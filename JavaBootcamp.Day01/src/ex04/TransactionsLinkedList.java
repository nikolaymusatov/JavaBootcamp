package ex04;

import java.util.LinkedList;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
  public TransactionsLinkedList() {
    this.transactionsLinkedList = new LinkedList<Transaction>();
  }

  public void add(Transaction transaction) {
    this.transactionsLinkedList.push(transaction);
  }

  public void remove(UUID id) throws TransactionNotFoundException {
    boolean notFound = true;
    for (Transaction t : this.transactionsLinkedList) {
      if (t.getId() == id) {
        this.transactionsLinkedList.remove(t);
        notFound = false;
      }
    }
    if (notFound)
      throw new TransactionNotFoundException();
  }

  public boolean contains(UUID id) {
    boolean notFound = true;
    for (Transaction t : this.transactionsLinkedList)
      if (t.getId() == id)
        notFound = false;
    return !notFound;
  }

  public Transaction[] toArray() {
    Transaction[] transactions = new Transaction[this.transactionsLinkedList.size()];
    for (int i = 0; i < this.transactionsLinkedList.size(); i++) {
      transactions[i] = this.transactionsLinkedList.get(i);
    }
    return transactions;
  }

  public void printContent() {
    for (Transaction t : this.transactionsLinkedList) {
      t.printContent();
    }
  }

  private LinkedList<Transaction> transactionsLinkedList;
}
