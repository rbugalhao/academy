package com.ctw.workstation.teammember.entity;

import java.util.List;
import java.util.stream.Collectors;

public class TeammemberMapper {

    public static TeammemberDTO toDTO(Teammember entity) {
        TeammemberDTO dto = new TeammemberDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTeamId(entity.getTeamId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setModifiedAt(entity.getModifiedAt());
        dto.setCtwId(entity.getCtwId());
        return dto;
    }

    public static Teammember toEntity(TeammemberDTO dto) {
        Teammember entity = new Teammember();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setTeamId(dto.getTeamId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setModifiedAt(dto.getModifiedAt());
        entity.setCtwId(dto.getCtwId());
        return entity;
    }

    public static List<Teammember> toEntityList(List<TeammemberDTO> dto) {
        return dto.stream().map(TeammemberMapper::toEntity).collect(Collectors.toList());
    }

    public static List<TeammemberDTO> toDTOList(List<Teammember> entity) {
        return entity.stream().map(TeammemberMapper::toDTO).collect(Collectors.toList());
    }

}
