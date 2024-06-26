package ex05;

interface UsersList {
  public int add(User user);

  public User getUserById(int id);

  public User getUserByIndex(int index);

  public int getUsersNumber();

  public void printContent();
}
