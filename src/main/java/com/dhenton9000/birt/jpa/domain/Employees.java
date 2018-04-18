/**
 * register this is classes/META-INF/persistence.xml
 */
package com.dhenton9000.birt.jpa.domain;


import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import static com.dhenton9000.jpa.util.EntityUtils.trimField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "Employees")
@ApiModel(description = "the employee entity")

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String jobTitle;
    private Integer reportsTo;
    private Offices offices;
    private Set<Customers> customers;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_employeenumber_seq")
    @SequenceGenerator(name = "employees_employeenumber_seq", sequenceName = "employees_employeenumber_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "EMPLOYEENUMBER", nullable = false)
    @ApiModelProperty(example = "1", required = true)
    public Integer getEmployeeNumber() {
        return this.employeeNumber;
    }

    public void setEmployeeNumber(Integer id) {
        this.employeeNumber = id;

    }

  
    /**
     * @return the lastName
     */
    @Column(name = "LASTNAME", length = 50)
    @ApiModelProperty(example = "Doe", required = true)
    @Basic(optional = false)
    public String getLastName() {
        return trimField(this.lastName);
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    @Column(name = "FIRSTNAME", length = 50)
    @ApiModelProperty(example = "Jane", required = true)
    @Basic(optional = false)
    public String getFirstName() {

        return trimField(firstName);
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the extension
     */
    @Column(name = "EXTENSION", length = 10)
    @ApiModelProperty(example = "9999", required = true)
    @Basic(optional = false)
    public String getExtension() {

        return trimField(extension);
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * @return the email
     */
    @Column(name = "EMAIL", length = 100)
    @ApiModelProperty(example = "jane.doe@whattiz.com", required = true)
    @Basic(optional = false)
    public String getEmail() {

        return trimField(email);

    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the jobTitle
     */
    @Column(name = "JOBTITLE", length = 50)
    @ApiModelProperty(example = "humble servant", required = true)
    @Basic(optional = false)
    public String getJobTitle() {

        return trimField(jobTitle);

    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return the reportsTo
     */
    @Column(name = "REPORTSTO", length = 50, nullable = true)
    public Integer getReportsTo() {
        return reportsTo;
    }

    /**
     * @param reportsTo the reportsTo to set
     */
    public void setReportsTo(Integer reportsTo) {
        this.reportsTo = reportsTo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.employeeNumber);
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
        final Employees other = (Employees) obj;
        if (!Objects.equals(this.employeeNumber, other.employeeNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "employeeNumber=" + employeeNumber + ", lastName=" + lastName + '}';
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "OFFICECODE", referencedColumnName = "OFFICECODE", nullable = false,
            insertable = false, updatable = false)
    @JsonBackReference
    public Offices getOffices() {
        return offices;
    }

    /**
     * @param offices the offices to set
     */
    public void setOffices(Offices offices) {
        this.offices = offices;
    }

    /**
     *  name in join column refer to target table customers
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SALESREPEMPLOYEENUMBER", referencedColumnName ="EMPLOYEENUMBER" , nullable = false)
    @JsonManagedReference
    public Set<Customers> getCustomers() {
        return customers;
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }

}
