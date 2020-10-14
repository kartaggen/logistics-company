package com.f97808.logisticscompany.controller;

import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class ClientController {

    private final ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping({"/client", "/client-incoming"})
    public String client() {
        return "client-incoming";
    }

    @GetMapping("/client-outgoing")
    public String clientOutgoing() {
        return "client-outgoing";
    }

    @ModelAttribute("allIncomingPackets")
    public List<Packet> populateAllIncomingPackets() {
        return this.clientService.getAllIncomingPackets();
    }

    @ModelAttribute("allOutgoingPackets")
    public List<Packet> populateAllOutgoingPackets() {
        return this.clientService.getAllOutgoingPackets();
    }
}
