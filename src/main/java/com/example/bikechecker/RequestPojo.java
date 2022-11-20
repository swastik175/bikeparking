package com.example.bikechecker;

import lombok.Data;

import java.util.Date;

@Data
public class RequestPojo {
    private Integer slotId;
    private String bikeNo;
    private Date entryTime;
}
