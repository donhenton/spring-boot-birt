/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.domain;

import java.util.Objects;
import static com.dhenton9000.jpa.util.EntityUtils.trimField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dhenton
 */
 
@ApiModel(description = "salesreport item")
@XmlRootElement
public class SalesReport implements Serializable{
    
    private final String firstName;
    private final String lastName;
    private final Double totalSales;
    private final Integer employeeNumber;
    
    
    
    public SalesReport(String firstName, String lastName, Double totalSales, Integer employeeNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalSales = totalSales;
        this.employeeNumber = employeeNumber;
    }

    /**
     * @return the firstName
     */
    @ApiModelProperty(example = "fred")
    public String getFirstName() {
        return trimField(firstName);
    }

    /**
     * @return the lastName
     */
    @ApiModelProperty(example = "farkel")
    public String getLastName() {
        return trimField(lastName);
    }

    /**
     * @return the totalSales
     */
    @ApiModelProperty(example = "1456.23")
    public Double getTotalSales() {
        return totalSales;
    }

    /**
     * @return the employeeNumber
     */
    @ApiModelProperty(example = "1002")
    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.employeeNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SalesReport other = (SalesReport) obj;
        if (!Objects.equals(this.employeeNumber, other.employeeNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SalesReport{" + "lastName=" + lastName + ", totalSales=" + totalSales + ", employeeNumber=" + employeeNumber + '}';
    }
    
    
    
    
}
