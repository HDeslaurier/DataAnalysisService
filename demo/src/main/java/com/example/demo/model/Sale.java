package com.example.demo.model;

import java.time.LocalDate;

public record Sale(long objectId, long courseId, String courseName,long saleId, LocalDate saleDate, LocalDate reportDate, long itemId, String itemDescription, float actualPrice, float quantity, float salesTax, String LMPField) {};