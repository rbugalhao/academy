package com.ctw.workstation.rackasset.entity;

import java.util.List;

public class RackAssetMapper {

    public static RackAssetDTO toDto(RackAsset entity) {
        RackAssetDTO dto = new RackAssetDTO();
        dto.setId(entity.getId());
        dto.setRackId(entity.getRackId());
        dto.setAssetTag(entity.getAssetTag());
        return dto;
    }

    public static RackAsset toEntity(RackAssetDTO dto) {
        RackAsset entity = new RackAsset();
        entity.setId(dto.getId());
        entity.setRackId(dto.getRackId());
        entity.setAssetTag(dto.getAssetTag());
        return entity;
    }

    public static List<RackAsset> toEntityList(List<RackAssetDTO> dtos) {
        return dtos.stream()
                .map(RackAssetMapper::toEntity)
                .toList();
    }

    public static List<RackAssetDTO> toDtoList(List<RackAsset> entityList) {
        return entityList.stream()
                .map(RackAssetMapper::toDto)
                .toList();
    }

}
