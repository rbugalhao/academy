package com.ctw.workstation.simple;

import org.assertj.core.api.Assertions;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // permite que o before e after all não sejam estáticos, podendo dessa maneira guardar um estado entre testes (posso aceder a mm instancia de um objecto criado no beforeAll em cada teste)
class HelloExtAcademy0Test {

    private static final Logger log = Logger.getLogger(HelloAcademyTest.class);

    @Spy
    ExternalMessageServiceImpl externalMessageService;

    @InjectMocks
    HelloExtAcademy0 helloExtAcademy;

    @BeforeAll
    void setup() {
        log.info("BeforeAll");
//        externalMessageServiceSpy = spy(new ExternalMessageServiceImpl());
//        helloExtAcademy = new HelloExtAcademy(externalMessageServiceSpy);
    }

    @Test
    @DisplayName("When providing a null name, a message from outer space should be returned")
    void provide_null_name(){
        //given
        String name = null;
        //when
        Mockito.doNothing()
                .when(externalMessageService)
                .fazAlgo();
        String actualName = helloExtAcademy.sayHello(name);
        //Then
        Assertions.assertThat(actualName)
                .as("valid message is returned even when provided an external provider")
                .isEqualTo("Hello from outer space");
    }


    @Test
    @DisplayName("When provided a valid name")
    void new_provide_valid_name(){
        //given
        String name = "Renan";
        when(externalMessageService.sayHelloFromOuterSpace(name))
                .thenReturn("Hello Renan");
        //When
        String actualName = helloExtAcademy.sayHello(name);
        //Then
        Assertions.assertThat(actualName)
                .isEqualTo("Hello Renan");

    }

    @Test
    void spy_test(){
//        ArrayList<String> strings = new ArrayList<>();
//
//        ArrayList<String> stringsSpy = spy(strings);
//
//        doReturn("Renan")
//                .when(stringsSpy)
//                .get(0);
    }





}