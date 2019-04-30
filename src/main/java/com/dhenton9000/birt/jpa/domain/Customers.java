/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.domain;



import static com.dhenton9000.jpa.util.EntityUtils.trimField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "customers")
@ApiModel(description = "the customers entity")

    
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Customers implements Serializable {

    private String customerName;
    private String contactFirstName;
    private String contactLastName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String stateName;
    private String country;
    private String postalCode;
    private Employees employee;
    private Float creditLimit;
    private Integer customerNumber;
    private Set<Orders> orders;
    private float salesTotal = 0;
    
  

    /**
     * @return the officeCode
     */
    @Id
    @Basic(optional = false)
    @Column(name = "CUSTOMERNUMBER", nullable = false)
    public Integer getCustomerNumber() {
        return this.customerNumber;
    }

    public void setCustomerNumber(Integer n) {
        this.customerNumber = n;
    }

    /**
     * @return the phone
     */
    @Column(name = "PHONE", length = 50)
    @ApiModelProperty(example = "222.444.3333", required = true)
    @Basic(optional = false)
    public String getPhone() {
        return trimField(phone);
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the addressLine1
     */
    @Column(name = "ADDRESSLINE1", length = 50)
    @ApiModelProperty(example = "200 S Main", required = true)
    @Basic(optional = false)
    public String getAddressLine1() {
        return trimField(addressLine1);
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    @Column(name = "ADDRESSLINE2", length = 50)
    @ApiModelProperty(example = "Apt 100", required = true)
    @Basic(optional = false)
    public String getAddressLine2() {
        return trimField(addressLine2);
    }

    /**
     * @param addressLine2 the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return the state
     */
    @Column(name = "STATE", length = 50)
    @ApiModelProperty(example = "200 S Main", required = true)
    @Basic(optional = false)
    public String getStateName() {
        return trimField(stateName);
    }

    /**
     * @param state the state to set
     */
    public void setStateName(String state) {
        this.stateName = state;
    }

    /**
     * @return the country
     */
    @Column(name = "COUNTRY", length = 50)
    @ApiModelProperty(example = "France", required = true)
    @Basic(optional = false)
    public String getCountry() {
        return trimField(country);
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the postalCode
     */
    @Column(name = "POSTALCODE", length = 10)
    @ApiModelProperty(example = "France", required = true)
    @Basic(optional = false)
    public String getPostalCode() {
        return trimField(postalCode);
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.getCustomerNumber());
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
        final Customers other = (Customers) obj;
        if (!Objects.equals(this.customerNumber, other.customerNumber)) {
            return false;
        }
        return true;
    }

    @Column(name = "CUSTOMERNAME", length = 50)
    @ApiModelProperty(example = "Customer Name", required = true)
    @Basic(optional = false)
    public String getCustomerName() {
        return trimField(customerName);
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(name = "CONTACTFIRSTNAME", length = 50)
    @ApiModelProperty(example = "Fred", required = true)
    @Basic(optional = false)
    public String getContactFirstName() {
        return trimField(contactFirstName);
    }

    /**
     * @param contactFirstName the contactFirstName to set
     */
    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    @Column(name = "CONTACTLASTNAME", length = 50)
    @ApiModelProperty(example = "Farkel", required = true)
    @Basic(optional = false)
    public String getContactLastName() {
        return trimField(contactLastName);
    }

    /**
     * @param contactLastName the contactLastName to set
     */
    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    @Column(name = "CITY", length = 50)
    @ApiModelProperty(example = "Los Pedros", required = true)
    @Basic(optional = false)
    public String getCity() {
        return trimField(city);
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }


    @Column(name = "CREDITLIMIT", nullable = true)
    @ApiModelProperty(example = "35", required = false)
    @Basic(optional = true)
    public Float getCreditLimit() {
        return creditLimit;
    }

    /**
     * @param creditLimit the creditLimit to set
     */
    public void setCreditLimit(Float creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * @return the orders, for REST don't want these calls
     * JsonIgnore means that this isn't touched by the deserialization
     */
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMERNUMBER")
    @JsonIgnore
    public Set<Orders> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    /**
     * @return the employee
     */
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "SALESREPEMPLOYEENUMBER", insertable = false, updatable=false, nullable = false)
    @JsonBackReference
    @Basic(optional = false)
    public Employees getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public void setSalesTotal(Float z) {
           this.salesTotal  = z;
    }


    @Transient
    public Float getSalesTotal() {
        return this.salesTotal;
    }

}
