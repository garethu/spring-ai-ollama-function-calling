package com.yootiful.functioncalling.repository;

import com.yootiful.functioncalling.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findByIdAndFirstNameIgnoreCaseAndLastNameIgnoreCase(Long id, String firstName, String lastName);

}
