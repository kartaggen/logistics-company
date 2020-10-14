package com.f97808.logisticscompany.controller;

import com.f97808.logisticscompany.entity.Employee;
import com.f97808.logisticscompany.entity.Office;
import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.model.*;
import com.f97808.logisticscompany.service.AdminService;
import com.f97808.logisticscompany.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@SessionAttributes({AdminController.GENPASS})
public class AdminController {
    static final String GENPASS = "genPass";

    private final AdminService adminService;
    private final EmployeeService employeeService;

    AdminController(AdminService adminService, EmployeeService employeeService) {
        this.adminService = adminService;
        this.employeeService = employeeService;
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @ModelAttribute("allOffices")
    public List<Office> populateAllOffices() {
        return this.adminService.getAllOffice();
    }

    @GetMapping("/admin/office")
    public String office() {
        return "admin/office";
    }

    @GetMapping("/admin/office-save")
    public String officeSave(Model model) {
        model.addAttribute("office", new OfficeDto());
        return "admin/office-save";
    }

    @GetMapping("/admin/office-update")
    public String officeUpdate(Model model, @RequestParam(name = "id") String id) {
        Office office = adminService.getOffice(id);
        if (office != null) {
            OfficeDto officeDto = new OfficeDto(office.getId(), office.getName(), office.getAddress());
            model.addAttribute("office", officeDto);
            return "admin/office-save";
        } else return "error";
    }

    @PostMapping("/admin/office-save")
    public String officeSave(@Valid @ModelAttribute("office") OfficeDto office, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/office-save";
        }

        if (office.getId() == 0) {
            if (this.adminService.registerOffice(office)) {
                return "redirect:office-save?createSuccess";
            } else {
                return "redirect:office-save?failure";
            }
        } else {
            if (this.adminService.updateOffice(office)) {
                return "redirect:office-save?updateSuccess";
            } else {
                return "redirect:office-save?failure";
            }
        }
    }

    @GetMapping("/admin/office-delete")
    public String officeDelete(@RequestParam(name = "id") String id) {
        if (adminService.deleteOffice(id)) {
            return "redirect:office?success";
        } else return "redirect:office?failure";
    }

    @ModelAttribute("allEmployees")
    public List<Employee> populateAllEmployees() {
        return this.adminService.getAllEmployees();
    }

    @GetMapping("/admin/employee")
    public String employee() {
        return "admin/employee";
    }

    @GetMapping("/admin/employee-save")
    public String employeeSave(Model model) {
        model.addAttribute("employee", new EmployeeDto());
        if (model.getAttribute(GENPASS) != null)
            model.addAttribute(GENPASS, model.getAttribute(GENPASS));
        return "admin/employee-save";
    }

    @GetMapping("/admin/employee-update")
    public String employeeUpdate(Model model, @RequestParam(name = "id") String id) {
        Employee employee = adminService.getEmployee(id);
        if (employee != null) {
            EmployeeDto employeeDto = new EmployeeDto(
                    employee.getId(),
                    employee.getUser().getFirstName(),
                    employee.getUser().getLastName(),
                    employee.getUser().getUsername(),
                    employee.getUser().getEmail(),
                    employee.getOffice().getId()
            );
            model.addAttribute("employee", employeeDto);
            return "admin/employee-save";
        } else return "error";
    }

    @PostMapping("/admin/employee-save")
    public String employeeSave(Model model, @Valid @ModelAttribute("employee") EmployeeDto employee, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/employee-save";
        }
        if (employee.getId() == 0) {
            String genPass = this.adminService.registerEmployee(employee);
            if (genPass != null) {
                model.addAttribute(GENPASS, genPass);
                return "redirect:employee-save?createSuccess";
            } else {
                return "redirect:employee-save?failure";
            }
        } else {
            if (this.adminService.updateEmployee(employee)) {
                return "redirect:employee-save?updateSuccess";
            } else {
                return "redirect:employee-save?failure";
            }
        }
    }

    @GetMapping("/admin/employee-delete")
    public String employeeDelete(@RequestParam(name = "id") String id) {
        if (adminService.deleteEmployee(id)) {
            return "redirect:employee?success";
        } else return "redirect:employee?failure";
    }

    @ModelAttribute("allClients")
    public List<User> populateAllClients() {
        return this.adminService.getAllClients();
    }

    @GetMapping("/admin/client")
    public String client() {
        return "admin/client";
    }

    @GetMapping("/admin/client-save")
    public String clientSave(Model model) {
        model.addAttribute("client", new ClientDto());
        if (model.getAttribute(GENPASS) != null)
            model.addAttribute(GENPASS, model.getAttribute(GENPASS));
        return "admin/client-save";
    }

    @GetMapping("/admin/client-update")
    public String clientUpdate(Model model, @RequestParam(name = "id") String id) {
        User client = adminService.getClient(id);
        if (client != null) {
            ClientDto clientDto = new ClientDto(
                    client.getId(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getUsername(),
                    client.getEmail()
            );
            model.addAttribute("client", clientDto);
            return "admin/client-save";
        } else return "error";
    }

    @PostMapping("/admin/client-save")
    public String clientSave(Model model, @Valid @ModelAttribute("client") ClientDto client, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/client-save";
        }
        if (client.getId() == 0) {
            String genPass = this.adminService.registerClient(client);
            if (genPass != null) {
                model.addAttribute(GENPASS, genPass);
                return "redirect:client-save?createSuccess";
            } else {
                return "redirect:client-save?failure";
            }
        } else {
            if (this.adminService.updateClient(client)) {
                return "redirect:client-save?updateSuccess";
            } else {
                return "redirect:client-save?failure";
            }
        }
    }

    @GetMapping("/admin/client-delete")
    public String clientDelete(@RequestParam(name = "id") String id) {
        if (adminService.deleteClient(id)) {
            return "redirect:client?success";
        } else return "redirect:client?failure";
    }

    @ModelAttribute("allPackets")
    public List<Packet> populateAllPackets() {
        return this.employeeService.getAllPackets();
    }

    @GetMapping("/admin/packet")
    public String packet() {
        return "admin/packet";
    }

    @GetMapping("/admin/packet-save")
    public String packetSave(Model model) {
        model.addAttribute("packet", new PacketDto());
        return "admin/packet-save";
    }

    @GetMapping("/admin/packet-update")
    public String packetUpdate(Model model, @RequestParam(name = "id") String id) {
        Packet packet = employeeService.getPacket(id);
        if (packet != null) {
            PacketDto packetDto = new PacketDto(
                    packet.getId(),
                    packet.getStatus(),
                    packet.getStatusDate(),
                    packet.getWeight(),
                    packet.getDeliveryPrice(),
                    packet.getSender().getId(),
                    packet.getRecipient().getId(),
                    packet.getIsOffice(),
                    packet.getAddress()
            );
            model.addAttribute("packet", packetDto);
            return "admin/packet-save";
        } else return "error";
    }

    @PostMapping("/admin/packet-save")
    public String packetSave(Model model, @Valid @ModelAttribute("packet") PacketDto packet, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/packet-save";
        }
        if (packet.getId() == 0) {
            if (this.employeeService.registerPacket(packet)) {
                return "redirect:packet-save?createSuccess";
            } else {
                return "redirect:packet-save?failure";
            }
        } else {
            if (this.employeeService.updatePacket(packet)) {
                return "redirect:packet-save?updateSuccess";
            } else {
                return "redirect:packet-save?failure";
            }
        }
    }

    @GetMapping("/admin/packet-delete")
    public String packetDelete(@RequestParam(name = "id") String id) {
        if (adminService.deletePacket(id)) {
            return "redirect:packet?success";
        } else return "redirect:packet?failure";
    }

    @GetMapping("/admin/account")
    public String account(Model model) {
        model.addAttribute("pass", new PasswordDto());
        return "admin/account";
    }

    @RolesAllowed(value = "ADMIN")
    @PostMapping("/admin/account-pass-change")
    public String accountPassChange(@Valid @ModelAttribute("pass") PasswordDto password, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/account";
        }

        if (this.adminService.changePassword(password)) {
            return "redirect:account?success";
        } else {
            return "redirect:account?failure";
        }
    }

    @GetMapping("/admin/report")
    public String report(Model model) {
        return "admin/report";
    }

    @GetMapping("/admin/report/registered-by")
    public String registeredBy(Model model, @RequestParam(value = "id") String id) {
        model.addAttribute("id", id);
        Employee employee = this.adminService.getEmployee(id);
        model.addAttribute("employee", "#" + employee.getId() + " " + employee.getUser().getUsername());
        model.addAttribute("packetsRegisteredBy", this.adminService.getPacketsRegisteredBy(this.adminService.getEmployee(id)));
        return "admin/report/registered-by";
    }

    @GetMapping("/admin/report/sent-not-delivered")
    public String sentNotDelivered(Model model) {
        model.addAttribute("packetsSentNotDelivered", this.adminService.getPacketsSentNotDelivered());
        return "admin/report/sent-not-delivered";
    }

    @GetMapping("/admin/report/sent-by")
    public String sentBy(Model model, @RequestParam(value = "id") String id) {
        model.addAttribute("id", id);
        User client = this.adminService.getClient(id);
        model.addAttribute("client", "#" + client.getId() + " " + client.getUsername());
        model.addAttribute("packetsSentBy", this.adminService.getPacketsSentBy(client));
        return "admin/report/sent-by";
    }

    @GetMapping("/admin/report/received-by")
    public String receivedBy(Model model, @RequestParam(value = "id") String id) {
        model.addAttribute("id", id);
        User client = this.adminService.getClient(id);
        model.addAttribute("client", "#" + client.getId() + " " + client.getUsername());
        model.addAttribute("packetsReceivedBy", this.adminService.getPacketsReceivedBy(client));
        return "admin/report/received-by";
    }

    @GetMapping("/admin/report/income")
    public String income(Model model, @RequestParam(value = "dateFrom") String dateFrom, @RequestParam(value = "dateThrough") String dateThrough) {
        IncomeDto incomeDto = this.adminService.getIncomeBetween(dateFrom, dateThrough);

        if (incomeDto != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");


            long longFrom = Long.parseLong(dateFrom);
            long longThrough = Long.parseLong(dateThrough);
            Date dateB = new Date(longFrom);
            Date dateA = new Date(longThrough);

            model.addAttribute("success", true);
            model.addAttribute("dateBefore", dateFormat.format(dateB));
            model.addAttribute("dateAfter", dateFormat.format(dateA));
            model.addAttribute("incomeDto", incomeDto);
        } else
            model.addAttribute("invalidDates", true);

        return "admin/report/income";
    }
}
