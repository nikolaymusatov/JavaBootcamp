package edu.school21.ORM;

@OrmEntity(table = "simple_car")
public class Car {
    @OrmColumnId
    private Long id;
    @OrmColumn(name = "brand", length = 10)
    private String brand;
    @OrmColumn(name = "model", length = 10)
    private String model;
    @OrmColumn(name = "year", length = 10)
    private String year;
    @OrmColumn(name = "power")
    private Integer power;
    @OrmColumn(name = "mileage")
    private Integer mileage;
    
    public Car() {}
    
    public Car(String brand, String model, String year, Integer power,
               Integer mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.power = power;
        this.mileage = mileage;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", power=" + power +
                ", mileage=" + mileage +
                '}';
    }
}


