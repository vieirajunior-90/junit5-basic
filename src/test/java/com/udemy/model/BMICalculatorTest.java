package com.udemy.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private String environment = "dev";

//    @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"}, delimiter = ',')

    @Nested
    class DietRecommendedTests {
        @ParameterizedTest(name = "weight = {0}, height = {1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        void shouldReturnTrueWhenDietRecommended(Double weight, Double height) {
            // Given
            // Weight and Height are passed as parameters to the test method.
            // When
            var recommended = BMICalculator.isDietRecommended(weight, height);

            // Then
            assertTrue(recommended);
        }

        @Test
        void shouldReturnFalseWhenDietNotRecommended() {
            // Given
            var weight = 50.0;
            var height = 1.92;

            // When
            var recommended = BMICalculator.isDietRecommended(weight, height);

            // Then
            assertFalse(recommended);
        }

        @Test
        void shouldThrowArithmeticExceptionWhenHeightIsEqualToZero() {
            // Given
            var weight = 89.0;
            var height = 0;

            // When
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

            // Then
            assertThrows(ArithmeticException.class, executable);
        }
    }

    @Nested
    @DisplayName("{{}} sample inner class")
    @DisabledOnOs(OS.WINDOWS)
    class FindCoderWithWorstBMITests {
        @Test
        void shouldReturnCoderWithWorstBMIWhenCoderListIsNotEmpty() {
            // Given
            List<Coder> coders = new ArrayList<>(List.of(
                    new Coder(1.80, 60.0),
                    new Coder(1.82, 98.0),
                    new Coder(1.82, 64.7)
            ));
            // When
            var coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
            // Then
            assertAll(
                    () -> assertEquals(1.82, coderWithWorstBMI.getHeight()),
                    () -> assertEquals(98.0, coderWithWorstBMI.getWeight())
            );
        }

        @Test
        void shouldReturnNullWhenCoderWithWorstBMIIsEmpty() {
            // Given
            List<Coder> coders = new ArrayList<>(List.of());
            // When
            var coderWithWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
            // Then
            assertNull(coderWithWorstBMI);
        }

        @Test
        @DisplayName(">>>> sample method display name")
        void shouldReturnCorrectBMIScoreWhenCoderListNotEmpty() {
            // Given
            List<Coder> coders = new ArrayList<>(List.of(
                    new Coder(1.80, 60.0),
                    new Coder(1.82, 98.0),
                    new Coder(1.82, 64.7)
            ));
            double[] expected = {18.52, 29.59, 19.53};

            // When
            var bmiScores = BMICalculator.getBMIScores(coders);

            // Then
            assertArrayEquals(expected, bmiScores);

        }

        @Test
        void shouldReturnCoderWithWorstBMIWhenCoderListHas10000Elements() {
            // Given
            assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
            var coders = new ArrayList<Coder>();
            for (var i = 0; i < 10000; i++) {
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }

            // When
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

            // Then
            assertTimeout(Duration.ofMillis(10), executable);
        }
    }

}