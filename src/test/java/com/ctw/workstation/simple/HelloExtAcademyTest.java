package com.ctw.workstation.simple;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class HelloExtAcademyTest {

    @Inject
    HelloExtAcademy helloExtAcademy;

    @Test
    @DisplayName("aaa")
    void say_hello(){
        //given
//        String name = "Renan";
//
//        stubFor(post(urlEqualTo("/external/hello"))
//                .willReturn(aResponse()
//                        .withHeader("Content-Type", "application/json")
//                        .withStatus(200)
//                        .withBody("{\"message\":\"Helo Renan\"}")
//                ));
//        //when
//        String actualName = helloExtAcademy.sayHello(name);
//        //then
//        Assertions.assertThat(actualName)
//                .isNotNull();

    }

}