package com.ctw.workstation.rack.entity;

import java.util.List;

public class RackMapper {

    public static RackDTO toDTO(Rack rack) {
        RackDTO dto = new RackDTO();
        dto.setId(rack.getId());
        dto.setSerialNumber(rack.getSerialNumber());
        dto.setTeamId(rack.getTeamId());
        dto.setStatus(rack.getStatus());
        dto.setCreatedAt(rack.getCreatedAt());
        dto.setModifiedAt(rack.getModifiedAt());
        dto.setDefaultLocation(rack.getDefaultLocation());
        return dto;
    }

    public static Rack toEntity(RackDTO dto) {
        Rack entity = new Rack();
        entity.setId(dto.getId());
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setTeamId(dto.getTeamId());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setModifiedAt(dto.getModifiedAt());
        entity.setDefaultLocation(dto.getDefaultLocation());
        return entity;
    }
    
    public static List<RackDTO> toDTOList(List<Rack> racks) {
        return racks.stream()
                .map(RackMapper::toDTO)
                .toList();
    }
    
    public static List<Rack> toEntityList(List<RackDTO> dtos) {
        return dtos.stream()
                .map(RackMapper::toEntity)
                .toList();
    }

}
