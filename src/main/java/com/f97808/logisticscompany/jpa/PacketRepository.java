package com.f97808.logisticscompany.jpa;

import com.f97808.logisticscompany.entity.Packet;
import com.f97808.logisticscompany.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PacketRepository extends CrudRepository<Packet, Integer> {
    List<Packet> findByEmployee(User employee);

    List<Packet> findBySender(User sender);

    List<Packet> findByRecipient(User recipient);

    List<Packet> findByStatus(int status);

    List<Packet> findAllByStatusDateAfterAndStatusDateBefore(Date after, Date before);
}