package com.example.bikechecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
