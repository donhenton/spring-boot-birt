package com.dhenton9000.birt.jpa.domain;

import java.util.Objects;
import static com.dhenton9000.jpa.util.EntityUtils.trimField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(description = "JSON DTO example")
@XmlRootElement
public class BasicCustomer implements Serializable {

    private final String customerName;
    private final String phone;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String stateName;
    private final String country;
    private final String postalCode;
    private final Integer customerNumber;

    public BasicCustomer(String customerName, String phone, String addressLine1,
            String addressLine2, String city, String stateName,
            String country, String postalCode, Integer customerNumber) {

        this.customerName = customerName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.stateName = stateName;
        this.country = country;
        this.postalCode = postalCode;
        this.customerNumber = customerNumber;
    }

    /**
     * @return trimField( the customerName
     */
    @ApiModelProperty(example = "fred")
    public String getCustomerName() {
        return trimField(customerName);
    }

    /**
     * @return trimField( the phone
     */
    @ApiModelProperty(example = "999-999-9999")
    public String getPhone() {
        return trimField(phone);
    }

    /**
     * @return trimField( the addressLine1
     */
    @ApiModelProperty(example = "100 S Main")
    public String getAddressLine1() {
        return trimField(addressLine1);
    }

    /**
     * @return trimField( the addressLine2
     */
    @ApiModelProperty(example = "Apt 2")
    public String getAddressLine2() {
        return trimField(addressLine2);
    }

    /**
     * @return trimField( the city
     */
    @ApiModelProperty(example = "Hong Kong")
    public String getCity() {
        return trimField(city);
    }

    /**
     * @return trimField( the stateName
     */
    @ApiModelProperty(example = "HI")
    public String getStateName() {
        return trimField(stateName);
    }

    /**
     * @return trimField( the country
     */
    @ApiModelProperty(example = "Germany")
    public String getCountry() {
        return trimField(country);
    }

    /**
     * @return trimField( the postalCode
     */
    @ApiModelProperty(example = "55555")
    public String getPostalCode() {
        return trimField(postalCode);
    }

    /**
     * @return trimField( the customerNumber
     */
    @ApiModelProperty(example = "33")
    public Integer getCustomerNumber() {
        return customerNumber;
    }

    @Override
    public String toString() {
        return "BasicCustomer{" + "customerName=" + customerName
                + ", phone=" + phone + ", city=" + city
                + ", customerNumber=" + customerNumber + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.customerName);
        hash = 89 * hash + Objects.hashCode(this.phone);
        hash = 89 * hash + Objects.hashCode(this.addressLine1);
        hash = 89 * hash + Objects.hashCode(this.addressLine2);
        hash = 89 * hash + Objects.hashCode(this.city);
        hash = 89 * hash + Objects.hashCode(this.country);
        hash = 89 * hash + Objects.hashCode(this.postalCode);
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
        final BasicCustomer other = (BasicCustomer) obj;
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.addressLine1, other.addressLine1)) {
            return false;
        }
        if (!Objects.equals(this.addressLine2, other.addressLine2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        return true;
    }
    
    

}
