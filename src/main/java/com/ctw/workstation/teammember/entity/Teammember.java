package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.team.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="TEAM_MEMBER")
public class Teammember {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teammemberIdGenerator")
    @SequenceGenerator(name = "teammemberIdGenerator", sequenceName = "SEQ_TEAM_MEMBER_ID")
    private Long id;

    @Column(name = "NAME", length = 20, nullable = false)
    @NotBlank(message="E não tem nome?")
    private String name;

    @Column(name = "CTW_ID", length = 10, nullable = false)
    @NotBlank(message="E não tem ctw_id?")
    private String ctwId;

//    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT", nullable = false)
    @CreationTimestamp
    private Date createdAt;

//    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFIED_AT", nullable = false)
    @CreationTimestamp
    private Date modifiedAt;

    @Column(name = "TEAM_ID", nullable = false)
    @NotNull(message="Falta o teamId zé.")
    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", updatable = false, insertable = false, nullable = false)
    @JsonIgnore
    public Team team;


    @OneToMany(mappedBy = "requesterId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Booking> bookings;


    public void updateTeammember(Teammember updatedTeammember) {
        this.name = updatedTeammember.getName();
        this.team = updatedTeammember.getTeam();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCtwId() {
        return ctwId;
    }

    public void setCtwId(String ctwId) {
        this.ctwId = ctwId;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
