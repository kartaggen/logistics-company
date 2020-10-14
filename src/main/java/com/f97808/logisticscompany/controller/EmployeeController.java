package com.f97808.logisticscompany.controller;

import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.model.PacketDto;
import com.f97808.logisticscompany.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {


    private final EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"/employee", "/employee-packet"})
    public String employee() {
        return "employee-packet";
    }

    @ModelAttribute("allClients")
    public List<User> populateAllClients() {
        return this.employeeService.getAllClients();
    }

    @ModelAttribute("allPackets")
    public List<Packet> populateAllPackets() {
        return this.employeeService.getAllPackets();
    }


    @GetMapping("/employee-packet-save")
    public String packetSave(Model model) {
        model.addAttribute("packet", new PacketDto());
        return "employee-packet-save";
    }

    @GetMapping("/employee-packet-update")
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
            return "employee-packet-save";
        } else return "error";
    }

    @PostMapping("/employee-packet-save")
    public String packetSave(Model model, @Valid @ModelAttribute("packet") PacketDto packet, BindingResult result) {
        if (result.hasErrors()) {
            return "employee-packet-save";
        }
        if (packet.getId() == 0) {
            if (this.employeeService.registerPacket(packet)) {
                return "redirect:employee-packet-save?createSuccess";
            } else {
                return "redirect:employee-packet-save?failure";
            }
        } else {
            if (this.employeeService.updatePacket(packet)) {
                return "redirect:employee-packet-save?updateSuccess";
            } else {
                return "redirect:employee-packet-save?failure";
            }
        }
    }
}
