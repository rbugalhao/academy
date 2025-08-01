package com.ctw.workstation.booking.boundary;


import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.BookingDTO;
import com.ctw.workstation.booking.entity.BookingMapper;
import com.ctw.workstation.exceptions.MyApplicationException;
import com.ctw.workstation.service.BookingRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.logmanager.MDC;

import java.net.URI;
import java.util.List;

@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingResource {

//    private static final Logger logger = Logger.getLogger(BookingResource.class);
    Logger logger = Logger.getLogger(BookingResource.class);


    @Inject
    BookingRepository bookingRepository;


    @GET
    public List<BookingDTO> getAllRacks() {

        logger.info("Get all bookings");
        logger.warn("Endpoint is marked for removal");
        logger.debug("Este e um log de debug");
        return BookingMapper.toDTOList(bookingRepository.getAll());
    }

    @GET
    @Path("/{id}")
    public BookingDTO getBookingById(@PathParam("id") Long id) {

        MDC.put("request.id", String.valueOf(id));
        MDC.put("request.path", "/bookings/");

        if ( id == null || id < 0 ) throw new MyApplicationException("Not valid id: "+id, Response.Status.NOT_FOUND);

        logger.debugv("Getting booking with id: {0}",id);

        Booking booking = bookingRepository.getBookingById(id);

        if (booking == null) {
            logger.debugv("Booking with id {0} not found",id);
            throw new MyApplicationException("Couldn't locate booking with id "+id, Response.Status.NOT_FOUND);
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        else logger.debugv("Got booking with id {0} and created at {1}",booking.getId(), booking.getCreatedAt());

        return BookingMapper.toDTO(booking);
    }

    @POST
    public Response addBooking(BookingDTO bookingDTO) {
        Booking booking = BookingMapper.toEntity(bookingDTO);
        try {
            bookingRepository.addBooking(booking);
            return Response.created(URI.create("/bookings/" + booking.getId())).entity(booking).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error adding booking: " + e.getMessage())
                    .build();
        }
    }




    @PUT
    @Path("/{id}")
    public Response updateBooking(BookingDTO updatedBookingDTO, @PathParam("id") Long id) {
        Booking updatedBooking = BookingMapper.toEntity(updatedBookingDTO);
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        bookingRepository.updateBooking(updatedBooking);
        return Response.ok(URI.create("/bookings/" + booking.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        bookingRepository.deleteBooking(id);
        return Response.ok(URI.create("/bookings/" + booking.getId())).build();
    }

}
