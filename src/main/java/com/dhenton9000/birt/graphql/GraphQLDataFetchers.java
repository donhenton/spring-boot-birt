package com.dhenton9000.birt.graphql;

import com.dhenton9000.birt.jpa.domain.Offices;
import com.dhenton9000.birt.jpa.domain.SalesReport;
import com.dhenton9000.birt.jpa.service.EmployeesService;
import com.dhenton9000.birt.jpa.service.OfficesService;
import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GraphQLDataFetchers {
    
    private static final Logger LOG = LoggerFactory.getLogger(GraphQLDataFetchers.class);

    @Autowired
    private OfficesService officesService;
    @Autowired
    private EmployeesService employeesService;

    private static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of("id", "book-1",
                    "name", "Harry Potter and the Philosopher's Stone",
                    "pageCount", "223",
                    "authorId", "author-1"),
            ImmutableMap.of("id", "book-2",
                    "name", "Moby Dick",
                    "pageCount", "635",
                    "authorId", "author-2"),
            ImmutableMap.of("id", "book-3",
                    "name", "Interview with the vampire",
                    "pageCount", "371",
                    "authorId", "author-3")
    );

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
           
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher<Offices> getOfficesByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            
            String officeId = dataFetchingEnvironment.getArgument("id");
            Offices offices = this.officesService.findOne(officeId);
            return offices;
        };
    }
    
    public DataFetcher<SalesReport> getEmployeeSaleReportDataFetcher() {
        return dataFetchingEnvironment -> {
           
            Integer employeeId= null;
            String eId = dataFetchingEnvironment.getArgument("employeeId");
            try {
            employeeId = Integer.parseInt(eId);
            }
            catch (Exception e){
                LOG.error("could not integer parse "+eId);
            }
             LOG.info("id is "+employeeId);
            SalesReport report = this.employeesService.getSalesData(employeeId);
            LOG.info(report.toString());
            return report;
        };
    }
    
}
