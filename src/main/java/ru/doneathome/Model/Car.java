package ru.doneathome.Model;

public class Car {

    private Long id;
    private String model;
    private String vendor;
    private int horsepower;

    public Car() {
    }

    public Car(Long id, String model, String vendor) {
        this.id = id;
        this.model = model;
        this.vendor = vendor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
}
