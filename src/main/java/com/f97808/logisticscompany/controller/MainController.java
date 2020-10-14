package com.f97808.logisticscompany.controller;

import com.f97808.logisticscompany.model.TrackPacketDto;
import com.f97808.logisticscompany.service.TrackingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final TrackingService trackingService;

    MainController(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/tracking")
    public String tracking(Model model, @RequestParam(value = "trackNumber") String trackNumber) {
        int tn = 0;
        try {
            tn = Integer.parseInt(trackNumber);
        } catch (Exception e) {
            model.addAttribute("invalidId", true);
            return "tracking";
        }
        model.addAttribute("trackNumber", tn);
        TrackPacketDto trackPacketDto = trackingService.findPacketById(tn);
        if (trackPacketDto != null) {
            model.addAttribute("trackPacketDto", trackPacketDto);
            model.addAttribute("success", true);
        } else {
            model.addAttribute("idNotFound", true);
        }
        return "tracking";
    }
}