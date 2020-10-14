package com.f97808.logisticscompany.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne(targetEntity = Office.class)
    @JoinColumn(name = "office_id", referencedColumnName = "id")
    private Office office;

    public Employee() {
    }

    public Employee(@NotNull User user, @NotNull Office office) {
        this.user = user;
        this.office = office;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                "userId=" + user.toString() +
                ", officeId='" + office.toString() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (this.user == employee.user) return true;
        if (employee.user == null || user.getClass() != employee.user.getClass()) return false;

        if (this.office == employee.office) return true;
        return !(employee.office == null || office.getClass() != employee.office.getClass());
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + office.hashCode();
        return result;
    }
}
