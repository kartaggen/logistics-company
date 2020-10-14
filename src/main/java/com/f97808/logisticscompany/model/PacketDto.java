package com.f97808.logisticscompany.model;

import com.f97808.logisticscompany.validation.PacketDateTime;
import com.f97808.logisticscompany.validation.ValidPacket;

import javax.validation.constraints.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ValidPacket
public class PacketDto {

    private int id;
    @Min(value = 1)
    @Max(value = 4)
    private int status;
    @PacketDateTime
    private String date;
    @DecimalMin(value = "0.000", message = "Weight has to be more than 0.000", inclusive = false)
    @DecimalMax(value = "1000.00", message = "Weight has to be less than or equal to 1000.000")
    @Digits(integer = 4, fraction = 3, message = "Weight has to be up to 4 digits before and 3 digits after the decimal separator!")
    private double weight;
    private double deliveryPrice;
    @Positive(message = "Please select a sender")
    private int sender;
    @Positive(message = "Please select a recipient")
    private int recipient;
    private boolean isOffice;
    @NotEmpty(message = "Please enter an address")
    private String address;

    public PacketDto() {
    }

    public PacketDto(@NotEmpty int id,
                     @Min(value = 1) @Max(value = 3) int status,
                     Date date,
                     @DecimalMin(value = "0.000", message = "Weight has to be more than 0.000", inclusive = false)
                     @DecimalMax(value = "1000.00", message = "Weight has to be less than or equal to 1000.000")
                     @Digits(integer = 4, fraction = 3, message = "Weight has to be up to 4 digits before and 3 digits after the decimal separator!") double weight,
                     double deliveryPrice,
                     @Positive(message = "Please select a sender") int sender,
                     @Positive(message = "Please select a sender") int recipient,
                     boolean isOffice,
                     @NotEmpty(message = "Please enter an address") String address) {
        this.id = id;
        this.status = status;
        this.date = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").format(date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        this.weight = weight;
        this.deliveryPrice = deliveryPrice;
        this.sender = sender;
        this.recipient = recipient;
        this.isOffice = isOffice;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public boolean getIsOffice() {
        return isOffice;
    }

    public void setIsOffice(boolean isOffice) {
        this.isOffice = isOffice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}