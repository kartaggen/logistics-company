package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.jpa.PacketRepository;
import com.f97808.logisticscompany.jpa.UserRepository;
import com.f97808.logisticscompany.model.Authorities;
import com.f97808.logisticscompany.model.PacketDto;
import com.f97808.logisticscompany.model.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final PacketRepository packetRepository;

    EmployeeServiceImpl(
            UserRepository userRepository,
            PacketRepository packetRepository) {
        this.userRepository = userRepository;
        this.packetRepository = packetRepository;
    }

    @Override
    public List<User> getAllClients() {
        Iterable<User> clients = this.userRepository.findByRole(Authorities.CLIENT);
        return StreamSupport.stream(clients.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Packet> getAllPackets() {
        Iterable<Packet> packets = this.packetRepository.findAll();
        return StreamSupport.stream(packets.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Packet getPacket(String id) {
        int packetId = Integer.parseInt(id);
        Optional<Packet> packet = packetRepository.findById(packetId);
        return packet.orElse(null);
    }

    @Override
    public boolean registerPacket(PacketDto packet) {
        try {
            Date date = Date.from(LocalDateTime.parse(packet.getDate(), DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());
            User employeeUser = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            Optional<User> employee = this.userRepository.findById(employeeUser.getId());
            Optional<User> sender = this.userRepository.findById(packet.getSender());
            Optional<User> recipient = this.userRepository.findById(packet.getRecipient());
            if (!employee.isPresent() || !sender.isPresent() || !recipient.isPresent()) return false;

            Packet newPacket = new Packet(
                    packet.getStatus(),
                    date,
                    packet.getDeliveryPrice(),
                    packet.getWeight(),
                    employee.get(),
                    sender.get(),
                    recipient.get(),
                    packet.getIsOffice(),
                    packet.getAddress());
            packetRepository.save(newPacket);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updatePacket(PacketDto packetDto) {
        try {
            Date date = Date.from(LocalDateTime.parse(packetDto.getDate(), DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());
            User employeeUser = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            Optional<User> employee = this.userRepository.findById(employeeUser.getId());
            Optional<User> sender = this.userRepository.findById(packetDto.getSender());
            Optional<User> recipient = this.userRepository.findById(packetDto.getRecipient());
            Optional<Packet> updatedPacket = packetRepository.findById(packetDto.getId());

            if (!employee.isPresent() || !sender.isPresent() || !recipient.isPresent() || !updatedPacket.isPresent())
                return false;

            Packet packet = updatedPacket.get();
            packet.setStatus(packetDto.getStatus());
            packet.setStatusDate(date);
            packet.setWeight(packetDto.getWeight());
            packet.setDeliveryPrice(packetDto.getDeliveryPrice());
            packet.setEmployee(employee.get());
            packet.setSender(sender.get());
            packet.setRecipient(recipient.get());
            packet.setIsOffice(packetDto.getIsOffice());
            packet.setAddress(packetDto.getAddress());
            packetRepository.save(packet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
