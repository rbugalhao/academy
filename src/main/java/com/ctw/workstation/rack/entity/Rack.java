package com.ctw.workstation.rack.entity;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.team.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_RACK")
public class Rack {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackIdGenerator")
    @SequenceGenerator(name = "rackIdGenerator", sequenceName = "SEQ_RACK_ID")
    private Long id;

    @Column(name = "SERIAL_NUMBER", length = 20, nullable = false)
    @NotBlank(message="E não tem serialNumber?")
    private String serialNumber;

    @Transient
    @JsonIgnore
    public Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;


//    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private Date createdAt;

//    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFIED_AT", nullable = false)
    @CreationTimestamp
    private Date modifiedAt;


    @Column(name = "DEFAULT_LOCATION", nullable = false)
    private String defaultLocation;

    @Column(name = "TEAM_ID", nullable = false)
    @NotNull(message="Falta o teamId zé.")
    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", updatable = false, insertable = false, nullable = false)
    @JsonIgnore
    public Team team;


    @OneToMany(mappedBy = "rackId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Booking> bookings;

    @OneToMany(mappedBy = "rackId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RackAsset> rackAssets;



    public void updateRack(Rack newRack) {
        this.serialNumber = newRack.getSerialNumber();
        this.status = newRack.getStatus();
        this.teamId = newRack.getTeamId();
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<RackAsset> getRackAssets() {
        return rackAssets;
    }

    public void setRackAssets(List<RackAsset> rackAssets) {
        this.rackAssets = rackAssets;
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

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }



    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
