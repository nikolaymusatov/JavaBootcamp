package ex05;

import java.util.UUID;

public interface TransactionsList {
  public void add(Transaction transaction);

  public Transaction remove(UUID id);

  public boolean contains(UUID id);

  public Transaction[] toArray();

  public void printContent();
}
