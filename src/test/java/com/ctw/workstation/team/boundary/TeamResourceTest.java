package com.ctw.workstation.team.boundary;

import com.ctw.workstation.service.TeamRepository;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.TeamDTO;
import io.quarkus.runtime.Quarkus;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TeamResourceTest {

    @InjectMock
    TeamRepository teamRepository;


    @Test
    @DisplayName("creates a new Team")
    void addTeam(){

    }
}