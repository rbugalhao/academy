package com.ctw.workstation.service;

import com.ctw.workstation.team.entity.Team;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {

    public List<Team> getAll() {
        return listAll();
    }

    @Transactional
    public void addTeam(Team team) {
        if (team.getCreatedAt() == null) {
            team.setCreatedAt(new Date());
        }
        if (team.getModifiedAt() == null) {
            team.setModifiedAt(new Date());
        }
        persist(team);
    }

//    @Transactional
//    public void addTeam(Team team) {
//        if (team.getCreatedAt() == null) {
//            team.setCreatedAt(new Date());
//        }
//        if (team.getModifiedAt() == null) {
//            team.setModifiedAt(new Date());
//        }
//        persist(team);
//    }

    public Team findById(Long id) {
        return find("id", id).firstResult();
    }

    @Transactional
    public void updateTeam(Team team) {
        if (team.getModifiedAt() == null) {
            team.setModifiedAt(new Date());
        }
        getEntityManager().merge(team);
    }

    // delete
    @Transactional
    public void deleteTeam(Long id) {
        delete("id", id);
    }



}
