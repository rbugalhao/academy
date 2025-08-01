package com.ctw.workstation.team.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.teammember.entity.Teammember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamIdGenerator")
    @SequenceGenerator(name = "teamIdGenerator", sequenceName = "SEQ_TEAM_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;


    @Column(name = "PRODUCT", nullable = false)
    private String product;

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


    @OneToMany(mappedBy = "teamId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rack> racks;


    @OneToMany(mappedBy = "teamId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Teammember> teammembers;

    public void updateTeam(Team team) {
        this.name = team.getName();
        this.product = team.getProduct();
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

    public List<Rack> getRacks() {
        return racks;
    }

    public void setRacks(List<Rack> racks) {
        this.racks = racks;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Teammember> getTeammembers() {
        return teammembers;
    }

    public void setTeammembers(List<Teammember> teammembers) {
        this.teammembers = teammembers;
    }
}
