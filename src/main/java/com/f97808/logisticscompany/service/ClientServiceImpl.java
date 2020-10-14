package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.PacketRepository;
import com.f97808.logisticscompany.model.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {
    private final PacketRepository packetRepository;

    ClientServiceImpl(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    @Override
    public List<Packet> getAllIncomingPackets() {
        User client = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Iterable<Packet> packets = this.packetRepository.findByRecipient(client);
        return StreamSupport.stream(packets.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Packet> getAllOutgoingPackets() {
        User client = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Iterable<Packet> packets = this.packetRepository.findBySender(client);
        return StreamSupport.stream(packets.spliterator(), false)
                .collect(Collectors.toList());
    }
}
