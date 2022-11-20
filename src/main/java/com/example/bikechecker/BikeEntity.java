package com.example.bikechecker;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bike_parking")
@Data
public class BikeEntity {
    @Id
    @GeneratedValue
    @Column(name = "slot_id")
    private Integer slot_id;
    @Column(name="bike_no")
    private String bike_no;
    @Column(name="entry_time")
    private Date entry_time;
    @Column(name = "exit_time")
    private Date exit_time;
    @Column(name = "slots_available")
    private Integer slots_available;
}
