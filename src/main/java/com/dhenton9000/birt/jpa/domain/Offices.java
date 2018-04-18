/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.domain;

 
import static com.dhenton9000.jpa.util.EntityUtils.trimField;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Offices")
@ApiModel(description = "the office entity")
@NamedQueries({
    @NamedQuery(name = "Offices.findAll", query = "SELECT e FROM Offices e"),
    @NamedQuery(name = "Offices.findByid", query = "SELECT e FROM Offices e WHERE e.officeCode = :id")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Offices implements Serializable {

    private String officeCode;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String stateName;
    private String country;
    private String postalCode;
    private String territory;
    private Set<Employees> employees;
 
 

    /**
     * @return the officeCode
     */
    @Id
    @Basic(optional = false)
    @Column(name = "OFFICECODE", nullable = false)
    public String getOfficeCode() {
        return trimField(officeCode);
    }

    /**
     * @param officeCode the officeCode to set
     */
    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
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

    /**
     * @return the territory
     */
    @Column(name = "TERRITORY", length = 10)
    @ApiModelProperty(example = "Northwest", required = true)
    @Basic(optional = false)
    public String getTerritory() {
        return trimField(territory);
    }

    /**
     * @param territory the territory to set
     */
    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.officeCode);
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
        final Offices other = (Offices) obj;
        if (!Objects.equals(this.officeCode, other.officeCode)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offices{" + "officeCode=" + officeCode + ", postalCode=" + postalCode + ", territory=" + territory + '}';
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "OFFICECODE", nullable = false)
    @JsonManagedReference
    public Set<Employees> getEmployees() {
 
        return employees;
    }

    /**
     * @param employees the employees to set
     */
    public void setEmployees(Set<Employees> employees) {
        this.employees = employees;
    }

}
