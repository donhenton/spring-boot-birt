package com.dhenton9000.birt.graphql;

import com.dhenton9000.birt.jpa.domain.Offices;
import com.dhenton9000.birt.jpa.domain.SalesReport;
import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.domain.security.dto.GroupDTO;
import com.dhenton9000.birt.jpa.service.EmployeesService;
import com.dhenton9000.birt.jpa.service.OfficesService;
import com.dhenton9000.birt.jpa.service.security.GroupsService;
import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dhenton9000.birt.jpa.domain.security.Users;
import com.dhenton9000.birt.jpa.service.security.ApplicationsService;
import com.dhenton9000.birt.jpa.service.security.UsersService;

@Component
public class GraphQLDataFetchers {
    
    private static final Logger LOG = LoggerFactory.getLogger(GraphQLDataFetchers.class);

    @Autowired
    private ApplicationsService applicationsService;
     @Autowired
    private UsersService usersService;
    @Autowired
    private OfficesService officesService;
    @Autowired
    private EmployeesService employeesService;
    @Autowired
    private GroupsService groupsService;

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
    
    public DataFetcher<String> userNameTranslator() {
        return dataFetchingEnvironment -> {
          Users u = (Users) dataFetchingEnvironment.getSource();
           return u.getUsername();
        };
    }
    
    
    public DataFetcher<List<GroupDTO>> getSecurityGroupsDataFetcher() {
        return dataFetchingEnvironment -> {
            List<Applications> apps = applicationsService.findAll();
            List<Users> users = usersService.findAll();
            List<GroupDTO> groupDTOs = groupsService.findAllGroupDTOs();
            groupDTOs.forEach(g -> {
               g.setWholeApplicationList(apps); 
                
               g.setWholeUserList(users);
                
            });
            return groupDTOs;
        };
    }
    
     public DataFetcher<List<SalesReport>> getAnnualReportDataFetcher() {
        return dataFetchingEnvironment -> {
 
            List<SalesReport> reports = this.employeesService.getSalesData();
        //  LOG.info(reports.toString());
            return reports;
        };
        
       
    }
     
     public DataFetcher<Float> getAnnualTotalSalesDataFetcher() {
         
         return dataFetchingEnvironment -> {
            
            SalesReport report = (SalesReport) dataFetchingEnvironment.getSource();
            int randomNum = ThreadLocalRandom.current().nextInt(3000, 9000 + 1);
            return     new Float(randomNum);
 
         };
         
     }
    
}
