
package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.entity.User;
import com.f97808.logisticscompany.model.PacketDto;

import java.util.List;

public interface EmployeeService {

    List<User> getAllClients();

    List<Packet> getAllPackets();

    Packet getPacket(String id);

    boolean registerPacket(PacketDto client);

    boolean updatePacket(PacketDto client);

}
