package com.ctw.workstation.team.boundary;

import com.ctw.workstation.service.TeamRepository;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.TeamDTO;
import com.ctw.workstation.team.entity.TeamMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@Path("/teams")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    TeamRepository teamRepository;


    @GET
    public List<TeamDTO> getAllTeams() {
//        return teamRepository.listAll();
        return TeamMapper.toDTOList(teamRepository.listAll());
    }

    @GET
    @Path("/{id}")
    public TeamDTO getTeamById(@PathParam("id") Long id) {
        Team team = teamRepository.findById(id);
        if (team == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return TeamMapper.toDTO(team);
    }


//    @POST
//    public Response addTeam(Team team) {
//        try {
//            teamRepository.addTeam(team);
//            return Response.created(URI.create("/teams/" + team.getId())).entity(team).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                    .entity("Error adding team: " + e.getMessage())
//                    .build();
//        }
//    }

    @POST
    public Response addTeam(TeamDTO teamDTO) {
        Team team = TeamMapper.toEntity(teamDTO);
        try {
            teamRepository.addTeam(team);
            return Response.created(URI.create("/teams/" + team.getId())).entity(team).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error adding team: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateTeam(TeamDTO updatedTeamDTO, @PathParam("id") Long id) {
        Team updatedTeam = TeamMapper.toEntity(updatedTeamDTO);
        Team team = teamRepository.findById(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        teamRepository.updateTeam(updatedTeam);
        return Response.ok(URI.create("/teams/" + team.getId())).build();
    }

//    @PUT
//    @Path("/{id}")
//    public Response updateTeam(Team updatedTeam, @PathParam("id") Long id) {
//        Team team = teamRepository.findById(id);
//        if (team == null) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        teamRepository.updateTeam(updatedTeam);
//        return Response.ok(URI.create("/teams/" + team.getId())).build();
//    }

    @DELETE
    @Path("/{id}")
    public Response deleteTeam(@PathParam("id") Long id) {
        Team team = teamRepository.findById(id);
        if (team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        teamRepository.deleteTeam(id);
        return Response.ok(URI.create("/teams/" + team.getId())).build();
    }


}