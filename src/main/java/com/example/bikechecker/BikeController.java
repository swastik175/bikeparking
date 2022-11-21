package com.example.bikechecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BikeController {
    private BikeService bikeService;

    @Autowired
    public void setBikeService(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @PostMapping("/get-slots")
    public String checkBikeSlots(@RequestBody RequestPojo requestPojo){
        return bikeService.getBikeSlots(requestPojo);
    }
    @GetMapping("/bike-exit")
    public String bikeExit(@RequestParam Integer slotNo){
        return bikeService.bikeExiting(slotNo);
    }
}
