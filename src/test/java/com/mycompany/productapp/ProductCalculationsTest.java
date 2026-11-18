/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.productapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for ProductCalculations (Q.2.6), using R3500 from the sample.
 */
public class ProductCalculationsTest {

    private static final double DELTA = 0.001;
    private ProductCalculations calc;

    @BeforeEach
    public void setUp() {
        calc = new ProductCalculations(3500);
    }

    // Unit Test 1: 3500 x 15% = 525
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        assertEquals(525.0, calc.GetVat(), DELTA);
    }

    // Unit Test 2: 3500 + 525 + 175 = 4200
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        assertEquals(4200.0, calc.GetTotal(), DELTA);
    }
}