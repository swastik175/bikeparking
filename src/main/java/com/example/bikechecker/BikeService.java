package com.example.bikechecker;

public interface BikeService {
    String getBikeSlots(RequestPojo requestPojo);

    String bikeExiting(Integer slotNo);
}
