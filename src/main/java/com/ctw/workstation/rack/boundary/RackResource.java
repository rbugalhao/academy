package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.RackDTO;
import com.ctw.workstation.rack.entity.RackMapper;
import com.ctw.workstation.service.RackRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.*;

@Path("/racks")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)
public class RackResource {

    @Inject
    RackRepository rackRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RackDTO> getAllRacks() {
        return RackMapper.toDTOList(rackRepository.listAll());
    }

    @POST
    public Response addRack(RackDTO rackDTO) {
        Rack rack = RackMapper.toEntity(rackDTO);
        try {
            rackRepository.addRack(rack);
            return Response.created(URI.create("/racks/" + rack.getId())).entity(rack).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error adding rack: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public RackDTO getRackById(@PathParam("id") Long id) {
        Rack rack = rackRepository.findById(id);
        if (rack == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return RackMapper.toDTO(rack);
    }

    @PUT
    @Path("/{id}")
    public Response updateRack(RackDTO updatedRackDTO, @PathParam("id") Long id) {
        Rack updatedRack = RackMapper.toEntity(updatedRackDTO);
        Rack rack = rackRepository.findById(id);
        if (rack == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        rackRepository.updateRack(updatedRack);
        return Response.ok(URI.create("/racks/" + rack.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRack(@PathParam("id") Long id) {
        Rack rack = rackRepository.findById(id);
        if (rack == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        rackRepository.deleteRack(id);
        return Response.ok(URI.create("/racks/" + rack.getId())).build();
    }



//    //    private static final Map<String, Rack> racks = new HashMap<>();
//    private final DAO dao = DAO.getInstance();
//    private static final AtomicInteger idGenerator = new AtomicInteger(1);
//
//    @POST
//    public Response addRack(@Valid Rack rack) {
//        String id = String.valueOf(idGenerator.getAndIncrement());
//        rack.setId(id);
//
//        dao.addRack(rack);
//
//        String rackLink = String.format("/racks/%s", id);
//
//        return Response.created(URI.create(rackLink)).entity(rack).build();
//    }
//
//    @GET
//    public List<Rack> getAllRacks(@QueryParam("status") String status){
//        List<Rack> filteredRacks = dao.getAllRacks();
//        if(status != null && !status.isEmpty()) {
//            filteredRacks = filteredRacks.stream()
//                    .filter(r -> r.getStatus().equals(status)).toList();
//
//        }
//
//        return filteredRacks;
//    }
//
//    @GET
//    @Path("/{id}")
//    @APIResponse(responseCode = "201", description = "Created")
//    public Response getRack(@PathParam("id") String id) {
//        Rack rack = dao.getRackById(id);
//        if (rack == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.status(Response.Status.OK).entity(rack).build();
//
//    }
//
//    @PUT
//    @Path("/{id}")
//    public Response updateRack(@PathParam("id") String id, Rack updatedRack) {
//        Rack existingRack = dao.getRackById(id);
//        if (existingRack == null) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("Rack with ID " + id + " not found.")
//                    .build();
//        }
//
//        existingRack.updateRack(updatedRack);
//
//        String rackLink = String.format("/racks/%s", id);
//
//        return Response.ok(URI.create(rackLink)).entity(existingRack).build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteRack(@PathParam("id") String id) {
//        Rack removedRack = dao.deleteRack(id);
//        if (removedRack == null) {
//            return Response.status(Response.Status.NOT_FOUND)
//                    .entity("Rack with ID " + id + " not found.")
//                    .build();
//        }
//
//        return Response.status(Response.Status.OK).entity(removedRack).build();
//    }

}
