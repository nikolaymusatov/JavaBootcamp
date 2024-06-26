package edu.school21.ORM;

public class Program {
    public static void main(String[] args) {
        OrmManager ormManager = new OrmManager();
        User user1 = new User("Joe", "Black", 20);
        Car car1 = new Car("Toyota", "Camry", "2012", 168, 33000);
        try {
            ormManager.save(user1);
            System.out.println(user1);
            
            user1.setFirstName(null);
            ormManager.update(user1);
            System.out.println(user1);
            
            System.out.println("User with id=1\t" + ormManager.findById(1L,
                    User.class));
            
            ormManager.save(car1);
            System.out.println(car1);
            
            car1.setBrand("Nissan");
            ormManager.update(car1);
            System.out.println(car1);
            
            System.out.println("Car with id=1\t" + ormManager.findById(1L,
                    Car.class));
            
            ormManager.dropTable(Car.class);
            ormManager.dropTable(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
