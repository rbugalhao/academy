package com.ctw.workstation.rackasset.boundary;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.rackasset.entity.RackAssetDTO;
import com.ctw.workstation.rackasset.entity.RackAssetMapper;
import com.ctw.workstation.service.RackAssetRepository;
import com.ctw.workstation.team.entity.Team;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/rackassets")
public class RackAssetResource {
    @Inject
    RackAssetRepository rackAssetRepository;

    @GET
    @Produces("application/json")
    public List<RackAssetDTO> getAll() {
        return RackAssetMapper.toDtoList(rackAssetRepository.getAll());
    }

    @POST
    public Response addRackAsset(RackAssetDTO rackAssetDTO) {
        RackAsset rackAsset = RackAssetMapper.toEntity(rackAssetDTO);
        try {
            rackAssetRepository.addRackAsset(rackAsset);
            return Response.created(URI.create("/rackassets/" + rackAsset.getId())).entity(rackAsset).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error adding rackasset: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public RackAssetDTO getRackAssetById(@PathParam("id") Long id) {
        RackAsset rackAsset = rackAssetRepository.findById(id);
        if (rackAsset == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return RackAssetMapper.toDto(rackAsset);
    }

    @PUT
    @Path("/{id}")
    public Response updateRackAsset(RackAssetDTO updatedRackAssetDTO, @PathParam("id") Long id) {
        RackAsset updatedRackAsset = RackAssetMapper.toEntity(updatedRackAssetDTO);
        RackAsset rackAsset = rackAssetRepository.findById(id);
        if (rackAsset == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        rackAssetRepository.updateRackAsset(updatedRackAsset);
        return Response.ok(URI.create("/rackassets/" + rackAsset.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRackAsset(@PathParam("id") Long id) {
        RackAsset rackAsset = rackAssetRepository.findById(id);
        if (rackAsset == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        rackAssetRepository.deleteRackAsset(id);
        return Response.ok(URI.create("/rackassets/" + rackAsset.getId())).build();
    }

}
