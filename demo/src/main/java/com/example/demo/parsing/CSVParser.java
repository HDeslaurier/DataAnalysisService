package com.example.demo.parsing;

import com.example.demo.model.Sale;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVParser {
    String[] HEADERS = {"ObjectId", "CourseID", "CourseName", "SaleID", "SaleDate", "ReportDate", "ItemID", "ItemDescription", "ActualPrice", "Quantity", "SalesTax", "LMPField"};

    public List<Sale> csvToSales () throws IOException, URISyntaxException {
        List<Sale> sales = new ArrayList<>();

        URL resource = CSVParser.class.getResource("/Louisville_Sales.csv");

        Reader in = new FileReader(Paths.get(resource.toURI()).toString());
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();

        Iterable<CSVRecord> records = csvFormat.parse(in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
        for(CSVRecord record : records) {
            Sale sale = new Sale(
                Long.parseLong(record.get("ObjectId")),
                Long.parseLong(record.get("CourseID")),
                record.get("CourseName"),
                Long.parseLong(record.get("SaleID")),
                LocalDate.parse(record.get("SaleDate").substring(0, 10), formatter),
                LocalDate.parse(record.get("SaleDate").substring(0, 10), formatter),
                Long.parseLong(record.get("ItemID")),
                record.get("ItemDescription"),
                convertFloat(record.get("ActualPrice")),
                convertFloat(record.get("Quantity")),
                convertFloat(record.get("Quantity")),
                record.get("LMPField")
            );
            sales.add(sale);
        }
        return sales;
    }

    private float convertFloat (String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }
}
