package com.ctw.workstation.team.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamDTO toDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setProduct(team.getProduct());
        dto.setCreatedAt(team.getCreatedAt());
        dto.setModifiedAt(team.getModifiedAt());
        dto.setDefaultLocation(team.getDefaultLocation());
        return dto;
    }

    public static Team toEntity(TeamDTO dto) {
        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        team.setProduct(dto.getProduct());
        team.setCreatedAt(dto.getCreatedAt());
        team.setModifiedAt(dto.getModifiedAt());
        team.setDefaultLocation(dto.getDefaultLocation());
        return team;
    }

    public static List<TeamDTO> toDTOList(List<Team> teams) {
        return teams.stream()
                .map(TeamMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Team> toEntityList(List<TeamDTO> dtos) {
        return dtos.stream()
                .map(TeamMapper::toEntity)
                .collect(Collectors.toList());
    }

}
