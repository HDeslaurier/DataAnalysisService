package com.example.demo.controller;

import com.example.demo.model.Month;
import com.example.demo.model.Product;
import com.example.demo.model.Sale;
import com.example.demo.parsing.CSVParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DataAnalysisController {
    @GetMapping("/sales")
    public List<Sale> getAllSales() throws IOException, URISyntaxException {
        CSVParser parser = new CSVParser();

        return parser.csvToSales();
    }

    @GetMapping("/products")
    public Map<Long, Product> getProducts() throws IOException, URISyntaxException {
        CSVParser parser = new CSVParser();
        List<Sale> sales = parser.csvToSales();

        Map<Long, Product> products = new HashMap<>();

        for(Sale sale : sales) {
            Product existingProduct = products.get(sale.itemId());
            if (existingProduct != null){
                products.put(sale.itemId(), new Product(sale.itemId(), sale.itemDescription(), existingProduct.totalQuantitySold() + sale.quantity(),
                        existingProduct.totalSales() + (sale.actualPrice() * sale.quantity())));
            } else {
                products.put(sale.itemId(), new Product(sale.itemId(), sale.itemDescription(), sale.quantity(), sale.actualPrice() * sale.quantity()));
            }
        }

        return products;
    }

    @GetMapping("/salesByMonth")
    public Map<Integer, Month> getSalesByMonth() throws IOException, URISyntaxException {
        CSVParser parser = new CSVParser();
        List<Sale> sales = parser.csvToSales();

        Map<Integer, Month> months = new HashMap<>();

        for(Sale sale : sales) {
            int saleMonth = sale.saleDate().getMonth().getValue();
            Month existingMonth = months.get(saleMonth);

            if (existingMonth != null){
                months.put(saleMonth, new Month(existingMonth.totalQuantitySold() + sale.quantity(),
                        existingMonth.totalSales() + (sale.actualPrice() * sale.quantity())));
            } else {
                months.put(saleMonth, new Month(sale.quantity(), sale.actualPrice() * sale.quantity()));
            }
        }

        return months;
    }
}