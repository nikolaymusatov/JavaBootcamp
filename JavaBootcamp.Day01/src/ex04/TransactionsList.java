package ex04;

import java.util.UUID;

public interface TransactionsList {
  public void add(Transaction transaction);

  public void remove(UUID id);

  public boolean contains(UUID id);

  public Transaction[] toArray();

  public void printContent();
}
