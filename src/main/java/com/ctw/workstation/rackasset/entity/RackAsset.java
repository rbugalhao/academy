package com.ctw.workstation.rackasset.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "T_RACK_ASSET")
public class RackAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackassetIdGenerator")
    @SequenceGenerator(name = "rackassetIdGenerator", sequenceName = "SEQ_RACK_ASSET_ID")
    private Long id;

    @Column(name = "ASSET_TAG", length = 20, nullable = false)
    @NotBlank(message="E não tem asset_tag?")
    private String assetTag;

    @Column(name = "RACK_ID", nullable = false)
    @NotNull(message="Falta o rackId zé.")
    private Long rackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RACK_ID", updatable = false, insertable = false, nullable = false)
    @JsonIgnore
    private Rack rack;

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }
}
