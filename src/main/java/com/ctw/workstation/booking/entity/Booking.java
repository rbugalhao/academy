package com.ctw.workstation.booking.entity;


import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.Teammember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "T_BOOKING")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingIdGenerator")
    @SequenceGenerator(name = "bookingIdGenerator", sequenceName = "SEQ_BOOKING_ID")
    private Long id;

    @Column(name = "BOOK_FROM", nullable = false)
    @CreationTimestamp
    private Date bookFrom;

    @Column(name = "BOOK_TO", nullable = false)
    @CreationTimestamp
    private Date bookTo;

    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "MODIFIED_AT", nullable = false)
    @CreationTimestamp
    private Date modifiedAt;

    @Column(name = "RACK_ID", nullable = false)
    @NotNull(message="Falta o rackId zé.")
    private Long rackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RACK_ID", updatable = false, insertable = false, nullable = false)
    @JsonIgnore
    private Rack rack;

    @Column(name = "REQUESTER_ID", nullable = false)
    @NotNull(message="Falta o requesterId zé.")
    private Long requesterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID", updatable = false, insertable = false, nullable = false)
    @JsonIgnore
    private Teammember requester;

    public void updateBooking(Booking booking) {
        this.rack = booking.getRack();
        this.requester = booking.getRequester();
        this.bookFrom = booking.getBookFrom();
        this.bookTo = booking.getBookTo();
    }

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookFrom() {
        return bookFrom;
    }

    public void setBookFrom(Date bookFrom) {
        this.bookFrom = bookFrom;
    }

    public Date getBookTo() {
        return bookTo;
    }

    public void setBookTo(Date bookTo) {
        this.bookTo = bookTo;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }

    public Teammember getRequester() {
        return requester;
    }

    public void setRequester(Teammember requester) {
        this.requester = requester;
    }
}
