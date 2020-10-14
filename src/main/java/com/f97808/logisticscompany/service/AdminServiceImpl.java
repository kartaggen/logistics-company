package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.Employee;
import com.f97808.logisticscompany.entity.Office;
import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.EmployeeRepository;
import com.f97808.logisticscompany.jpa.OfficeRepository;
import com.f97808.logisticscompany.jpa.PacketRepository;
import com.f97808.logisticscompany.jpa.UserRepository;
import com.f97808.logisticscompany.model.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdminServiceImpl implements AdminService {

    private final OfficeRepository officeRepository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PacketRepository packetRepository;
    private final PasswordEncoder encoder;

    AdminServiceImpl(OfficeRepository officeRepository,
                     UserRepository userRepository,
                     EmployeeRepository employeeRepository,
                     PacketRepository packetRepository,
                     PasswordEncoder encoder) {
        this.officeRepository = officeRepository;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.packetRepository = packetRepository;
        this.encoder = encoder;
    }

    private String generatePassword() {
        char[] SYMBOLS = ("^$*.[]{}()?-!@#%&/,><':;|_~").toCharArray();
        char[] LOWERCASE = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
        char[] UPPERCASE = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        char[] NUMBERS = ("0123456789").toCharArray();
        Random rand = new SecureRandom();

        Character[] password = new Character[10];

        password[0] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[1] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[2] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        password[3] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[4] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[5] = UPPERCASE[rand.nextInt(UPPERCASE.length)];
        password[6] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[7] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[8] = NUMBERS[rand.nextInt(NUMBERS.length)];
        password[9] = SYMBOLS[rand.nextInt(SYMBOLS.length)];

        List<Character> charList = Arrays.asList(password);
        Collections.shuffle(charList);
        return charList.stream().map(Objects::toString).collect(Collectors.joining(""));
    }

    @Override
    public List<Office> getAllOffice() {
        Iterable<Office> offices = this.officeRepository.findAll();
        return StreamSupport.stream(offices.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Office getOffice(String id) {
        int officeId = Integer.parseInt(id);
        Optional<Office> office = officeRepository.findById(officeId);
        return office.orElse(null);
    }

    @Override
    public boolean registerOffice(OfficeDto office) {
        Office newOffice = new Office(office.getName(), office.getAddress());
        officeRepository.save(newOffice);
        return true;
    }

    @Override
    public boolean updateOffice(OfficeDto office) {
        Optional<Office> updatedOffice = officeRepository.findById(office.getId());
        if (updatedOffice.isPresent()) {
            updatedOffice.get().setName(office.getName());
            updatedOffice.get().setAddress(office.getAddress());
            officeRepository.save(updatedOffice.get());
            return true;
        } else return false;
    }

    @Override
    public boolean deleteOffice(String id) {
        try {
            int officeId = Integer.parseInt(id);
            Optional<Office> office = officeRepository.findById(officeId);
            if (office.isPresent()) {
                if (employeeRepository.findByOffice(office.get()).isEmpty()) {
                    officeRepository.delete(office.get());
                    return true;
                }
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        Iterable<Employee> employees = this.employeeRepository.findAll();
        return StreamSupport.stream(employees.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployee(String id) {
        int employeeId = Integer.parseInt(id);
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.orElse(null);
    }

    @Override
    public String registerEmployee(EmployeeDto employee) {
        String genPass = generatePassword();

        User newUser = new User(employee.getUsername(),
                encoder.encode(genPass),
                employee.getEmail(),
                employee.getFirstname(),
                employee.getLastname(),
                new Date(),
                Authorities.EMPLOYEE);
        userRepository.save(newUser);
        User savedUser = userRepository.findByUsername(employee.getUsername());
        Optional<Office> foundOffice = officeRepository.findById(employee.getOffice());
        if (foundOffice.isPresent() && savedUser != null) {
            Employee newEmployee = new Employee(savedUser, foundOffice.get());
            employeeRepository.save(newEmployee);
            return genPass;
        }
        return null;
    }

    @Override
    public boolean updateEmployee(EmployeeDto employee) {
        Optional<Employee> updatedEmployee = employeeRepository.findById(employee.getId());
        if (updatedEmployee.isPresent()) {
            Optional<Office> newOffice = officeRepository.findById(employee.getOffice());
            if (newOffice.isPresent()) {
                User user = updatedEmployee.get().getUser();
                user.setFirstName(employee.getFirstname());
                user.setLastName(employee.getLastname());
                user.setEmail(employee.getEmail());
                user.setUsername(employee.getUsername());
                userRepository.save(user);
                Employee employeeForUpdate = updatedEmployee.get();
                employeeForUpdate.setOffice(newOffice.get());
                employeeRepository.save(employeeForUpdate);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) {
        try {
            int employeeId = Integer.parseInt(id);
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            if (employee.isPresent()) {
                if (packetRepository.findByEmployee(employee.get().getUser()).isEmpty()) {
                    employeeRepository.delete(employee.get());
                    userRepository.delete(employee.get().getUser());
                    return true;
                }
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public List<User> getAllClients() {
        Iterable<User> clients = this.userRepository.findByRole(Authorities.CLIENT);
        return StreamSupport.stream(clients.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public User getClient(String id) {
        int clientId = Integer.parseInt(id);
        Optional<User> client = userRepository.findById(clientId);
        return client.orElse(null);
    }

    @Override
    public String registerClient(ClientDto client) {
        String genPass = generatePassword();

        User newUser = new User(client.getUsername(),
                encoder.encode(genPass),
                client.getEmail(),
                client.getFirstname(),
                client.getLastname(),
                new Date(),
                Authorities.CLIENT);
        userRepository.save(newUser);
        User savedUser = userRepository.findByUsername(client.getUsername());
        if (savedUser != null) {
            return genPass;
        }
        return null;
    }

    @Override
    public boolean updateClient(ClientDto client) {
        Optional<User> updatedClient = userRepository.findById(client.getId());
        if (updatedClient.isPresent()) {
            User user = updatedClient.get();
            user.setFirstName(client.getFirstname());
            user.setLastName(client.getLastname());
            user.setEmail(client.getEmail());
            user.setUsername(client.getUsername());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteClient(String id) {
        try {
            int clientId = Integer.parseInt(id);
            Optional<User> client = userRepository.findById(clientId);
            if (client.isPresent()) {
                if (packetRepository.findBySender(client.get()).isEmpty() && packetRepository.findByRecipient(client.get()).isEmpty()) {
                    userRepository.delete(client.get());
                    return true;
                }
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean deletePacket(String id) {
        try {
            int packetId = Integer.parseInt(id);
            Optional<Packet> packet = packetRepository.findById(packetId);
            if (packet.isPresent()) {
                packetRepository.deleteById(packetId);
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean changePassword(PasswordDto password) {
        List<User> admins = userRepository.findByRole("ADMIN");
        if (admins.size() == 1) {
            User admin = admins.get(0);
            admin.setPassword(encoder.encode(password.getNewPass()));
            userRepository.save(admin);
            return true;
        }
        return false;
    }

    @Override
    public List<Packet> getPacketsRegisteredBy(Employee employee) {
        Iterable<Packet> packets = this.packetRepository.findByEmployee(employee.getUser());
        return StreamSupport.stream(packets.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Packet> getPacketsSentNotDelivered() {
        Iterable<Packet> packetsProcessing = this.packetRepository.findByStatus(1);
        Iterable<Packet> packetsOnTheWay = this.packetRepository.findByStatus(2);
        Iterable<Packet> packetsNotReceived = this.packetRepository.findByStatus(4);
        List<Packet> allPackets = StreamSupport.stream(packetsProcessing.spliterator(), false)
                .collect(Collectors.toList());
        allPackets.addAll(StreamSupport.stream(packetsOnTheWay.spliterator(), false)
                .collect(Collectors.toList()));
        allPackets.addAll(StreamSupport.stream(packetsNotReceived.spliterator(), false)
                .collect(Collectors.toList()));
        return allPackets;
    }

    @Override
    public List<Packet> getPacketsSentBy(User user) {
        Iterable<Packet> packets = this.packetRepository.findBySender(user);
        return StreamSupport.stream(packets.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Packet> getPacketsReceivedBy(User user) {
        Iterable<Packet> packets = this.packetRepository.findByRecipient(user);
        return StreamSupport.stream(packets.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public IncomeDto getIncomeBetween(String from, String through) {
        try {
            long longFrom = Long.parseLong(from);
            long longThrough = Long.parseLong(through);
            Date dateFrom = new Date(longFrom);
            Date dateThrough = new Date(longThrough);
            List<Packet> packets = this.packetRepository.findAllByStatusDateAfterAndStatusDateBefore(dateFrom, dateThrough);
            if (packets.isEmpty()) return new IncomeDto(0, 0, 0, 0, 0, 0, 0, 0);
            else {
                double income = 0;
                double weightAll = 0;
                int packetsAll = packets.size();
                int packets1 = 0;
                int packets2 = 0;
                int packets3 = 0;
                int packets4 = 0;
                int toOffice = 0;

                for (Packet packet : packets) {
                    income += packet.getDeliveryPrice();
                    weightAll += packet.getWeight();
                    if (packet.getStatus() == 1) packets1++;
                    else if (packet.getStatus() == 2) packets2++;
                    else if (packet.getStatus() == 3) packets3++;
                    else packets4++;
                    if (packet.getIsOffice()) toOffice++;
                }

                return new IncomeDto(income, weightAll, packetsAll, packets1, packets2, packets3, packets4, toOffice);
            }
        } catch (Exception e) {
            return null;
        }
    }

}
