package com.ctw.workstation.booking.entity;

import java.util.List;
import java.util.stream.Collectors;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setRackId(booking.getRackId());
        bookingDTO.setRequesterId(booking.getRequesterId());
        bookingDTO.setBookFrom(booking.getBookFrom());
        bookingDTO.setBookTo(booking.getBookTo());
        bookingDTO.setCreatedAt(booking.getCreatedAt());
        bookingDTO.setModifiedAt(booking.getModifiedAt());
        return bookingDTO;
    }

    public static Booking toEntity(BookingDTO dto){
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setRackId(dto.getRackId());
        booking.setRequesterId(dto.getRequesterId());
        booking.setBookFrom(dto.getBookFrom());
        booking.setBookTo(dto.getBookTo());
        booking.setCreatedAt(dto.getCreatedAt());
        booking.setModifiedAt(dto.getModifiedAt());
        return booking;
    }

    public static List<Booking> toEntityList(List<BookingDTO> dtos){
        return dtos.stream().map(BookingMapper::toEntity).collect(Collectors.toList());
    }

    public static List<BookingDTO> toDTOList(List<Booking> bookings){
        return bookings.stream().map(BookingMapper::toDTO).collect(Collectors.toList());
    }

}
