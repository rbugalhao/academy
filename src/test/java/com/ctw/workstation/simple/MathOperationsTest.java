package com.ctw.workstation.simple;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // permite que o before e after all não sejam estáticos, podendo dessa maneira guardar um estado entre testes (posso aceder a mm instancia de um objecto criado no beforeAll em cada teste)

class MathOperationsTest {

    private static final Logger log = Logger.getLogger(HelloAcademyTest.class);


    MathOperations mathOperations;

    @BeforeAll
    void beforeAll(){
        log.info("Before All");
        mathOperations =  new MathOperations();
    }

    public static Stream<Arguments> provide_valid_number_add() {
        return Stream.of(
                Arguments.of(1,1,2),
                Arguments.of(1,2,3),
                Arguments.of(2,1,3),
                Arguments.of(0,0,0),
                Arguments.of(0,1,1),
                Arguments.of(2,-1,1),
                Arguments.of(-2,-1,-3)
        );
    }

    @ParameterizedTest
    @MethodSource()
    void provide_valid_number_add(int a, int b, int expected) {
        // given
        // when
        int result = mathOperations.add(a,b);
        // then
        assertAll(
                () -> assertEquals(expected, result)
        );
    }


    public static Stream<Arguments> provide_valid_numbers_divide() {
        return Stream.of(
                Arguments.of(1,1,1),
                Arguments.of(1,2,0),
                Arguments.of(4,2,2),
                Arguments.of(10,3,3),
                Arguments.of(2,-1,-2),
                Arguments.of(-2,-1,2),
                Arguments.of(-2,-2,1),
                Arguments.of(0,-2,0)
        );
    }

    @ParameterizedTest
    @MethodSource()
    void provide_valid_numbers_divide(int dividend, int divisor, int expected) {
        int result = dividend/divisor;

        assertEquals(expected, result);
    }

    @Test
    void provide_zero_divisor_divide() {
        int dividend = 1;
        int divisor = 0;
        assertThrows(ArithmeticException.class, () -> {mathOperations.divide(dividend,divisor);});
    }

}