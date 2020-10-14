package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.Packet;

import java.util.List;

public interface ClientService {
    List<Packet> getAllIncomingPackets();

    List<Packet> getAllOutgoingPackets();
}
