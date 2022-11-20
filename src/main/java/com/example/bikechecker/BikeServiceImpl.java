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
            //TODO change the timing
            bikeEntity.setEntry_time(Calendar.getInstance().getTime());
            bikeEntity.setExit_time(null);
            bikeEntity.setSlots_available(--count);
            bikeRepository.save(bikeEntity);
            Long parkingTime = byId.get().getExit_time().getTime() - byId.get().getEntry_time().getTime();
            double totalParkingAmount = parkingAmountPerHour * parkingTime;
            return "Vehicle inserted into parking area" + "Total Parking Amount for the parked bike is " + totalParkingAmount;
        }
    }
}
