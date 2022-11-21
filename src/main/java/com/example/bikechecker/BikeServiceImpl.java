package com.example.bikechecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;

@Service
public class BikeServiceImpl implements BikeService {

    private Integer parkingAmountPerHour = 50;
    private BikeRepository bikeRepository;

    @Autowired
    public void setBikeRepository(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public String getBikeSlots(RequestPojo requestPojo) {
        BikeEntity bikeEntity = new BikeEntity();
        Optional<BikeEntity> byId = bikeRepository.findById(requestPojo.getSlotId());
        if (byId.get().getSlots_available() >= 100) {
            throw new RuntimeException("No slots available right now!");
        } else {
            int count = byId.get().getSlots_available();
            bikeEntity.setBike_no(requestPojo.getBikeNo());
            bikeEntity.setEntry_time(Calendar.getInstance().getTime());
            bikeEntity.setExit_time(null);
            bikeEntity.setSlots_available(--count);
            bikeRepository.save(bikeEntity);
            return "Bike inserted into the parking lot";
        }
    }

    @Override
    public String bikeExiting(Integer slotNo) {
        Optional<BikeEntity> existingBike = bikeRepository.findById(slotNo);
        int count = existingBike.get().getSlots_available();
        existingBike.get().setExit_time(Calendar.getInstance().getTime());
        existingBike.get().setSlots_available(++count);
        bikeRepository.save(existingBike.get());
        Integer parkingTime = existingBike.get().getExit_time().getHours()- existingBike.get().getEntry_time().getHours();
        Long totalParkingAmount = Long.valueOf(parkingTime * parkingAmountPerHour);
        return "Vehicle exited from the parking lot " + "Total Parking Amount for the bike with slot no " + slotNo + "is : " + totalParkingAmount;
    }
}
