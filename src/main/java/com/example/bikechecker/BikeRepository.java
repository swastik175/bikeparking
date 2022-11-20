package com.example.bikechecker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<BikeEntity,Integer> {
}
