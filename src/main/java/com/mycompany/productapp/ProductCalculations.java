/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productapp;

/**
 * Calculates VAT, commission and total sale amount (Q.2.5).
 */
public class ProductCalculations implements IProductCalculations {

    // Rates as constants so they can be changed in one place
    private static final double VAT_RATE = 0.15;
    private static final double COMMISSION_RATE = 0.05;

    private final double price;

    public ProductCalculations(double price) {
        this.price = price;
    }

    @Override
    public double GetVat() {
        return price * VAT_RATE;
    }

    @Override
    public double GetCommission() {
        return price * COMMISSION_RATE;
    }

    // Re-uses the methods above so the rates aren't repeated
    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}
