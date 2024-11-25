package com.yootiful.functioncalling.mapper;

import com.yootiful.functioncalling.entities.Booking;
import com.yootiful.functioncalling.model.BookingRecord;

public class BookingMapper {

    public static BookingRecord toRecord(Booking booking) {
        return new BookingRecord(
                booking.getId(),
                booking.getFirstName(),
                booking.getLastName(),
                booking.getTravelDate(),
                booking.getTravelFrom(),
                booking.getTravelTo(),
                booking.getStatus(),
                booking.getBookingClass()
        );
    }
}
