package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.BookingDTO;
import com.ctw.workstation.booking.entity.BookingMapper;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.Status;
import com.ctw.workstation.rackasset.entity.RackAsset;
import com.ctw.workstation.service.*;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.teammember.entity.Teammember;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import jakarta.inject.Inject;
import org.apache.http.HttpStatus;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestHTTPEndpoint(BookingResource.class)
class BookingResourceTest {

    @Inject
    TeamRepository teamRepository;
    @Inject
    TeammemberRepository teammemberRepository;
    @Inject
    RackRepository rackRepository;
    @Inject
    RackAssetRepository rackAssetRepository;
    @Inject
    BookingRepository bookingRepository;

    @BeforeAll
    void setup() {
        Team team = new Team();
        team.setName("Team 1");
        team.setProduct("Product 1");
        teamRepository.addTeam(team);

        Teammember teammember = new Teammember();
        teammember.setTeamId(1L);
        teammember.setCtwId("ctw04344");
        teammember.setName("rodrigo");
        teammemberRepository.addTeammember(teammember);

        Rack rack = new Rack();
        rack.setTeamId(1L);
        rack.setSerialNumber("1111-22222-33");
        rack.setStatus(Status.AVAILABLE);
        rackRepository.addRack(rack);

        RackAsset rackAsset = new RackAsset();
        rackAsset.setRackId(1L);
        rackAsset.setAssetTag("tag1");
        rackAssetRepository.addRackAsset(rackAsset);

//        Booking booking = new Booking();
//        booking.setRackId(1L);
//        booking.setRequesterId(1L);
//        bookingRepository.addBooking(booking);


    }




    public Stream<Arguments> addBooking() {
        return Stream.of(
                Arguments.of("1"),
                Arguments.of("2")
        );
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource
    @DisplayName("create booking")
    void addBooking(String expected_id) {
        //given

        //when

        //then
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setRackId(1L);
        bookingDTO.setRequesterId(1L);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(bookingDTO)
                .when()
                .post("")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location" , endsWith(expected_id));

    }



    @Order(2)
    @Test
    @DisplayName("getting a booking by id")
    void getBookingById() {

        BookingDTO bookingDTO = RestAssured.given()
                .pathParam("id", 1L)
                .when()
                .get("/{id}")
                .then()
                .extract()
                .body()
                .as(BookingDTO.class);

        System.out.println(bookingDTO.getId());

        BookingDTO expectedBookingDTO = BookingMapper.toDTO(bookingRepository.getBookingById(1L));
        assertThat(bookingDTO).usingRecursiveComparison().isEqualTo(expectedBookingDTO);

    }

    @Order(3)
    @Test
    @DisplayName("get all the bookings")
    void getAllBookings() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesRegex(".*\"id\":1.*"))
                .body(matchesRegex(".*\"id\":2.*"));

    }



}