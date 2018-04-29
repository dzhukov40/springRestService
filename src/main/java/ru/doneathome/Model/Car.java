package ru.doneathome.Model;

public class Car {

    private int id;
    private String model;
    private String vendor;

    public Car() {
    }

    public Car(int id, String model, String vendor) {
        this.id = id;
        this.model = model;
        this.vendor = vendor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
