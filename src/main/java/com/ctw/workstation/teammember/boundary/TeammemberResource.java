package com.ctw.workstation.teammember.boundary;

import com.ctw.workstation.service.TeammemberRepository;
import com.ctw.workstation.teammember.entity.Teammember;
import com.ctw.workstation.teammember.entity.TeammemberDTO;
import com.ctw.workstation.teammember.entity.TeammemberMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/temmembers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeammemberResource {

    @Inject
    TeammemberRepository teammemberRepository;

    @GET
    public List<TeammemberDTO> getAllTeammembers() {
        return TeammemberMapper.toDTOList(teammemberRepository.listAll());
    }


    @POST
    public Response addTeammember(TeammemberDTO teammemberDTO) {
        Teammember teammember = TeammemberMapper.toEntity(teammemberDTO);
        try {
            teammemberRepository.addTeammember(teammember);
            return Response.created(URI.create("/teammembes/" + teammember.getId())).entity(teammember).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error adding teammember: " + e.getMessage())
                    .build();
        }
    }

    //get by id
    @GET
    @Path("/{id}")
    public TeammemberDTO getTeammemberById(@PathParam("id") Long id) {
        Teammember teammember = teammemberRepository.getById(id);
        if (teammember == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return TeammemberMapper.toDTO(teammember);
    }

    //put

    @PUT
    @Path("/{id}")
    public Response updateTeammember(TeammemberDTO updatedTeammemberDTO, @PathParam("id") Long id) {
        Teammember updatedTeammember = TeammemberMapper.toEntity(updatedTeammemberDTO);
        Teammember teammember = teammemberRepository.findById(id);
        if (teammember == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        teammemberRepository.updateTeammember(updatedTeammember);
        return Response.ok(URI.create("/teammembers/" + teammember.getId())).build();
    }

    //delete
    @DELETE
    @Path("/{id}")
    public Response deleteTeammember(@PathParam("id") Long id) {
        Teammember teammember = teammemberRepository.findById(id);
        if (teammember == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        teammemberRepository.deleteTeammember(id);
        return Response.ok(URI.create("/teammembers/" + teammember.getId())).build();
    }

//    //private static final Map<String, Teammember> teammembers = new HashMap<>();
//    private final DAO dao = DAO.getInstance();
//    private static final AtomicInteger idGenerator = new AtomicInteger(1);
//
//
//    @POST
//    public Response addTeammember(Teammember teammember) {
//        String id = String.valueOf(idGenerator.getAndIncrement());
//        teammember.setId(id);
//        //teammembers.put(id, teammember);
//        dao.addTeammember(teammember);
//
//        String teammemberLink = String.format("/teammembers/%s", id);
//
//        return Response.created(URI.create(teammemberLink)).entity(teammember).build();
//    }
//
//    @GET
//    public List<Teammember> getAllTeammembers() {
//        return dao.getAllTeammembers();
//    }
//
//    @GET
//    @Path("/{id}")
//    public Response getTeammember(@PathParam("id") String id) {
//        Teammember teammember = dao.getTeammemberById(id);
//        if (teammember == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.status(Response.Status.OK).entity(teammember).build();
//    }
//
//    @PUT
//    @Path("/{id}")
//    public Response updateTeammember(@PathParam("id") String id, Teammember updatedTeammember) {
//        Teammember  teammember = dao.getTeammemberById(id);
//        if (teammember == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        teammember.updateTeammember(updatedTeammember);
//
//        String teammemberLink = String.format("/teammembers/%s", id);
//
//        return Response.ok(URI.create(teammemberLink)).entity(teammember).build();
//    }
//
//    @DELETE
//    @Path("/{id}")
//    public Response deleteTeammember(@PathParam("id") String id) {
//        Teammember teammember = dao.deleteTeammember(id);
//        if (teammember == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        return Response.status(Response.Status.OK).entity(teammember).build();
//    }

}
