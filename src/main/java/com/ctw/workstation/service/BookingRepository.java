package com.ctw.workstation.service;

import com.ctw.workstation.booking.entity.Booking;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class BookingRepository implements PanacheRepository<Booking> {

    public List<Booking> getAll() {
        return listAll();
    }

    @Transactional
    public void addBooking(Booking booking) {
        if (booking.getCreatedAt() == null) {
            booking.setCreatedAt(new Date());
        }
        if (booking.getModifiedAt() == null) {
            booking.setModifiedAt(new Date());
        }
        if (booking.getBookFrom() == null) {
            booking.setBookFrom(new Date());
        }
        if (booking.getBookTo() == null) {
            booking.setBookTo(new Date());
        }

        persist(booking);
    }

    public Booking getBookingById(Long id) {
        return findById(id);
    }

    public List<Booking> findByRackId(Long rackId) {
        return list("rackId", rackId);
    }

    public List<Booking> findByRequesterId(Long requesterId) {
        return list("requesterId", requesterId);
    }


    @Transactional
    public void updateBooking(Booking booking) {
        if (booking.getModifiedAt() == null) {
            booking.setModifiedAt(new Date());
        }
        if (booking.getBookFrom() == null) {
            booking.setBookFrom(new Date());
        }
        if (booking.getBookTo() == null) {
            booking.setBookTo(new Date());
        }
        getEntityManager().merge(booking);
    }

    // delete
    @Transactional
    public void deleteBooking(Long id) {
        delete("id", id);
    }

}
