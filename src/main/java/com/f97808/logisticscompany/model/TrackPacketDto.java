package com.f97808.logisticscompany.model;

public class TrackPacketDto {

    private int id;
    private String status;
    private String date;
    private double weight;
    private double deliveryPrice;

    public TrackPacketDto() {
    }

    public TrackPacketDto(int id, int status, String date, double weight, double deliveryPrice) {
        this.id = id;

        if (status == 1) this.status = "Processing";
        else if (status == 2) this.status = "On the way";
        else if (status == 3) this.status = "Delivered";
        else this.status = "Not received";

        this.date = date;
        this.weight = weight;
        this.deliveryPrice = deliveryPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
}
