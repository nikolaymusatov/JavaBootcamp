package ex02;

public class UsersArrayList implements UsersList {
  public UsersArrayList() {
    users = new User[10];
    usersNumber = 0;
  }

  public int getUsersNumber() {
    return this.usersNumber;
  }

  public void addUser(User user) {
    if (this.isFull())
      resizeList();
    this.users[this.usersNumber] = user;
    this.usersNumber++;
  }

  public User getUserById(int id) throws UserNotFoundException {
    User user = null;
    boolean loopEnded = false;
    for (int i = 0; i < this.usersNumber; i++) {
      if (this.users[i].getId() == id) {
        user = this.users[i];
        break;
      }
      if (i == this.usersNumber - 1) {
        loopEnded = true;
      }
    }
    if (loopEnded) {
      throw new UserNotFoundException("User not found!");
    }
    return user;
  }

  public User getUserByIndex(int index) {
    User user = null;
    if (index < this.usersNumber && index >= 0)
      user = users[index];
    else
      throw new ArrayIndexOutOfBoundsException("Index is out of range!");
    return user;
  }

  public boolean isFull() {
    return this.usersNumber == this.users.length ? true : false;
  }

  public void printContent() {
    for (int i = 0; i < this.usersNumber; i++) {
      users[i].printContent();
    }
  }

  private void resizeList() {
    User[] tmp = new User[(int) (this.users.length * 1.5)];
    for (int i = 0; i < this.users.length; i++) {
      tmp[i] = this.users[i];
    }
    this.users = tmp;
  }

  private User[] users;
  private int usersNumber;
}
