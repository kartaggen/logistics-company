package com.f97808.logisticscompany.service;

import com.f97808.logisticscompany.model.TrackPacketDto;

public interface TrackingService {
    TrackPacketDto findPacketById(int id);
}
