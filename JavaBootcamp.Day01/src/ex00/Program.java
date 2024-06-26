package ex00;

import ex00.Transaction.TransferCategory;

public class Program {
  public static void main(String[] args) {
    User John = new User(100, "John", 1000);
    User Mike = new User(101, "Mike", 1000);
    Transaction trans1 = new Transaction(John, Mike, TransferCategory.OUTCOME,
        -500);
    Transaction trans2 = new Transaction(Mike, John, TransferCategory.INCOME,
        500);

    John.printContent();
    Mike.printContent();
    trans1.printContent();
    trans2.printContent();
  }
}
