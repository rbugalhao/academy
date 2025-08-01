package com.ctw.workstation.service;


import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.Teammember;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class TeammemberRepository implements PanacheRepository<Teammember> {

    public List<Teammember> getAll() {
        return listAll();
    }


    public Teammember findByName(String name) {
        return find("name", name).firstResult();
    }

    @Transactional
    public void addTeammember(Teammember teammember) {
        if (teammember.getCreatedAt() == null) {
            teammember.setCreatedAt(new Date());
        }
        if (teammember.getModifiedAt() == null) {
            teammember.setModifiedAt(new Date());
        }
        persist(teammember);
    }

    public Teammember getById(Long id) {
        return find("id", id).firstResult();
    }

    // update
    @Transactional
    public void updateTeammember(Teammember teammember) {
        if (teammember.getModifiedAt() == null) {
            teammember.setModifiedAt(new Date());
        }
        getEntityManager().merge(teammember);
    }

    // delete
    @Transactional
    public void deleteTeammember(Long id) {
        delete("id", id);
    }

}
