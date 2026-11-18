/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productapp;

/**
 * Contract for the sale calculations (Q.2.5).
 */
public interface IProductCalculations {

    // VAT = 15% of the price
    double GetVat();

    // Commission = 5% of the price
    double GetCommission();

    // Total = price + VAT + commission
    double GetTotal();
}