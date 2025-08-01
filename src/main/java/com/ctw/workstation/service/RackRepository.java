package com.ctw.workstation.service;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.Status;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.Teammember;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

@ApplicationScoped

public class RackRepository implements PanacheRepository<Rack> {

    public List<Rack> getAll() {
        return listAll();
    }

    public Rack findById(Long id) {
        return find("id", id).firstResult();
    }

    public List<Rack> getAllFilteredByStatus(Status status) {
        return find("status", status).list();
    }

    @Transactional
    public void addRack(Rack rack) {
        if (rack.getCreatedAt() == null) {
            rack.setCreatedAt(new Date());
        }
        if (rack.getModifiedAt() == null) {
            rack.setModifiedAt(new Date());
        }
        persist(rack);
    }


    @Transactional
    public void updateRack(Rack rack) {
        if (rack.getModifiedAt() == null) {
            rack.setModifiedAt(new Date());
        }
        getEntityManager().merge(rack);
    }

    // delete
    @Transactional
    public void deleteRack(Long id) {
        delete("id", id);
    }


}
