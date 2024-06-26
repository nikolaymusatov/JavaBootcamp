public class Program {
  public static void main(String[] args) {
    program(479598);
  }

  public static void program(int num) {
    int sum = 0;
    while (num > 0) {
      sum += num % 10;
      num /= 10;
    }
    System.out.println(sum);
  }
}