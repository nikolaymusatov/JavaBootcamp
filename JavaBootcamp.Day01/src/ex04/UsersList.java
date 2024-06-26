package ex04;

interface UsersList {
  public void add(User user);

  public User getUserById(int id);

  public User getUserByIndex(int index);

  public int getUsersNumber();

  public void printContent();
}
