package ru.doneathome.Model;

public class Car {

    private Long carId;
    private String model;
    private String vendor;

    public Car() {
    }

    public Car(Long carId, String model, String vendor) {
        this.carId = carId;
        this.model = model;
        this.vendor = vendor;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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
