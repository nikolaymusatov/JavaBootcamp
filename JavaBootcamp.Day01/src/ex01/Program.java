package ex01;

public class Program {
  public static void main(String[] args) {
    User Mike = new User("Mike", 1000);
    User John = new User("John", 1300);
    User Peter = new User("Peter", 1200);
    Mike.printContent();
    John.printContent();
    Peter.printContent();
  }

}
