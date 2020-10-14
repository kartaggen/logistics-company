package com.f97808.logisticscompany.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "packet")
public class Packet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Min(value = 1)
    @Max(value = 4)
    @Column(name = "status")
    private int status; //1 - Processing, 2 - On the way, 3 - Delivered, 4 - Not received

    @Column(name = "status_date")
    private Date statusDate;

    @Positive
    @Column(name = "weight")
    private double weight;

    @Positive
    @Column(name = "delivery_price")
    private double deliveryPrice;

    @NotNull
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "employee_user_id", referencedColumnName = "id")
    private User employee;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private User recipient;

    @Column(name = "is_office")
    private boolean isOffice;

    @NotBlank
    @Column(name = "address", length = 100)
    private String address;

    public Packet() {
    }

    public Packet(@NotBlank int status, @NotBlank Date statusDate, double weight, double deliveryPrice, @NotNull User employee, @NotNull User sender, @NotNull User recipient, boolean isOffice, @NotBlank String address) {
        this.status = status;
        this.statusDate = statusDate;
        this.weight = weight;
        this.deliveryPrice = deliveryPrice;
        this.employee = employee;
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

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
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

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", statusDate='" + statusDate.toString() + '\'' +
                ", weight='" + weight + '\'' +
                ", deliveryPrice=" + deliveryPrice +
                ", employee=" + employee.toString() +
                ", sender=" + sender.toString() +
                ", recipient=" + recipient.toString() +
                ", isOffice='" + isOffice + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Packet packet = (Packet) o;

        if (id != packet.id) return false;
        if (status != packet.status) return false;
        if (Double.compare(packet.weight, weight) != 0) return false;
        if (Double.compare(packet.deliveryPrice, deliveryPrice) != 0) return false;
        if (isOffice != packet.isOffice) return false;
        if (address.equals(packet.address)) return false;

        if (this.employee == packet.employee) return true;
        if (packet.employee == null || employee.getClass() != packet.employee.getClass()) return false;

        if (this.sender == packet.sender) return true;
        if (packet.sender == null || sender.getClass() != packet.sender.getClass()) return false;

        if (this.recipient == packet.recipient) return true;
        if (packet.recipient == null || recipient.getClass() != packet.recipient.getClass()) return false;

        return statusDate.equals(packet.statusDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + status;
        result = 31 * result + statusDate.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(deliveryPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + employee.hashCode();
        result = 31 * result + sender.hashCode();
        result = 31 * result + recipient.hashCode();
        result = 31 * result + Boolean.valueOf(isOffice).hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
