package com.ctw.workstation.simple;

import org.assertj.core.api.Assertions;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS) // permite que o before e after all não sejam estáticos, podendo dessa maneira guardar um estado entre testes (posso aceder a mm instancia de um objecto criado no beforeAll em cada teste)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HelloAcademyTest {

    private static final Logger log = Logger.getLogger(HelloAcademyTest.class);

    HelloAcademy helloAcademy;

    @BeforeAll
    void beforeAll() {
        helloAcademy = new HelloAcademy();
        log.info("Before All");
    }
    @BeforeEach
    void setUp() {
        log.info("Before Each");
    }

    @AfterEach
    void tearDown() {
        log.info("After Each");
    }

    @AfterAll
    void afterAll() {
        log.info("After All");
    }


    public static Stream<Arguments> provide_name() {
        return Stream.of(
                Arguments.of("Renan", "Hello Renan"),
                Arguments.of("João", "Hello João"),
                Arguments.of("Maria", "Hello Maria")
        );
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource()
    @DisplayName("When providing a name, \"Hello %name%\" should be returned, unless it's null, then it should return just \"Hello\"")
    void provide_name(String name, String expected){
        // given

        // when
        String actualName = helloAcademy.sayHello(name);

        // then
        assertThat(actualName).isNotNull().isEqualTo(expected);
//        assertAll(
//                () -> assertNotNull(actualName),
//                () -> assertEquals(expected, actualName, "Return name should be just \"Hello\" provided a null name.")
//        );
    }

    @Order(2)
    @Test
    void provide_null_name(){
        // given
        String name = null;

        // when
        String actualName = helloAcademy.sayHello(null);

        // then
//        assertAll(
//                () -> assertNotNull(actualName),
//                () -> assertEquals("Hello", actualName, "Return name should be \"Hello \" provided an empty name.")
//        );
    }

    @Order(3)
    @Test
    void provide_empty_name(){
        String name = "";
        String actualName = helloAcademy.sayHello(name);
//        assertAll(
//                () -> assertNotNull(actualName),
//                () -> assertEquals("Hello ", actualName, "Return name should be just \"Hello\" provided a null name.")
//        );
    }



}