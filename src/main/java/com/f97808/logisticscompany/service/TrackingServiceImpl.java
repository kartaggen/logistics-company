package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.jpa.PacketRepository;
import com.f97808.logisticscompany.model.TrackPacketDto;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TrackingServiceImpl implements TrackingService {
    private final PacketRepository packetRepository;

    TrackingServiceImpl(PacketRepository packetRepository) {
        this.packetRepository = packetRepository;
    }

    @Override
    public TrackPacketDto findPacketById(int id) {
        Optional<Packet> packet = packetRepository.findById(id);
        if (packet.isPresent()) {
            String date = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy").format(packet.get().getStatusDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
            return new TrackPacketDto(packet.get().getId(),
                    packet.get().getStatus(),
                    date,
                    packet.get().getWeight(),
                    packet.get().getDeliveryPrice());
        }
        return null;
    }
}
