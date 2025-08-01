package com.ctw.workstation.service;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class RackAssetRepository implements PanacheRepository<RackAsset> {

    public List<RackAsset> getAll() {
        return listAll();
    }

    public RackAsset findById(Long id) {
        return find("id", id).firstResult();
    }

    public RackAsset findByRackId(Long rackId) {
        return find("rackId", rackId).firstResult();
    }

    @Transactional
    public void addRackAsset(RackAsset rackAsset) {
        persist(rackAsset);
    }

    @Transactional
    public void updateRackAsset(RackAsset rackAsset) {
        getEntityManager().merge(rackAsset);
    }

    // delete
    @Transactional
    public void deleteRackAsset(Long id) {
        delete("id", id);
    }

}
