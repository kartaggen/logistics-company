package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.Employee;
import com.f97808.logisticscompany.entity.Office;
import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.model.*;

import java.util.List;

public interface AdminService {

    //-----Offices CRUD
    List<Office> getAllOffice();
    Office getOffice(String id);
    boolean registerOffice(OfficeDto office);
    boolean updateOffice(OfficeDto office);
    boolean deleteOffice(String id);


    //-----Employees CRUD
    List<Employee> getAllEmployees();
    Employee getEmployee(String id);
    String registerEmployee(EmployeeDto employee);
    boolean updateEmployee(EmployeeDto employee);
    boolean deleteEmployee(String id);


    //-----Clients CRUD
    List<User> getAllClients();
    User getClient(String id);
    String registerClient(ClientDto client);
    boolean updateClient(ClientDto client);
    boolean deleteClient(String id);

    //-----Packet Delete
    boolean deletePacket(String id);

    //-----Admin password change
    boolean changePassword(PasswordDto password);

    //-----Reports
    List<Packet> getPacketsRegisteredBy(Employee employee);
    List<Packet> getPacketsSentNotDelivered();
    List<Packet> getPacketsSentBy(User user);
    List<Packet> getPacketsReceivedBy(User user);
    IncomeDto getIncomeBetween(String dateFrom, String dateTo);
}
