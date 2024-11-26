package com.yootiful.functioncalling.config;

import com.yootiful.functioncalling.entities.Booking;
import com.yootiful.functioncalling.mapper.BookingMapper;
import com.yootiful.functioncalling.model.BookingRecord;
import com.yootiful.functioncalling.service.BookingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

@Configuration
public class FunctionConfiguration {
    private final BookingService bookingService;

    public FunctionConfiguration(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public record BookingRequest(Long id, String firstName, String lastName) {}

    @Bean
    @Description("Get booking details by id, which should be an integer, first name and last name")
    public Function<BookingRequest, BookingRecord> getBookingDetails() {
        return request -> {
            Booking booking = bookingService.getBookingByIdAndFirstNameAndLastName(request.id(), request.firstName(), request.lastName());
            if (booking != null) {
                return BookingMapper.toRecord(booking);
            } else {
                return null;
            }
        };
    }
}