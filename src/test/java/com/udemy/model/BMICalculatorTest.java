package com.udemy.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void shouldReturnTrueWhenDietRecommended() {
        // Given
        var weight = 89.0;
        var height = 1.72;

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
}