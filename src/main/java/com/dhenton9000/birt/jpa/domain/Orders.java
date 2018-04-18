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
import java.util.Date;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import springfox.documentation.annotations.ApiIgnore;

@Entity
@Table(name = "orders")
@ApiModel(description = "the orders entity")
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT e FROM Orders e"),
    @NamedQuery(name = "Orders.findByid", query = "SELECT e FROM Orders e WHERE e.orderNumber = :id")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Orders implements Serializable  {

    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;
    private Customers customer;
    private Integer orderNumber;
    private Set<OrderDetails> orderDetails;
 
 

    @Id
    @Basic(optional = false)
    @Column(name = "ORDERNUMBER", nullable = false)
    public Integer getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(Integer n) {
        this.orderNumber = n;
    }

    @Column(name = "ORDERDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(example = "2/2/2014", required = true)
    @Basic(optional = false)
    public Date getorderDate() {
        return this.orderDate;
    }

    /**
     * @param orderDate the order date to set
     */
    public void setorderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the requiredDate
     */
    @Column(name = "REQUIREDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(example = "2/22/2013", required = true)
    @Basic(optional = false)
    public Date getRequiredDate() {
        return requiredDate;
    }

    /**
     * @param rDate the date to set
     */
    public void setRequiredDate(Date rDate) {
        this.requiredDate = rDate;
    }

    /**
     * @return the shippedDate
     */
    @Column(name = "SHIPPEDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(example = "2/22/2013", required = true)
    @Basic(optional = false)
    public Date getShippedDate() {
        return shippedDate;
    }

    /**
     * @param rDate the date to set
     */
    public void setShippedDate(Date rDate) {
        this.shippedDate = rDate;
    }

    @Column(name = "STATUS", length = 15)
    @ApiModelProperty(example = "DONE", required = true)
    @Basic(optional = false)
    public String getStatus() {
        return trimField(status);
    }

    /**
     * @param st the status to set
     */
    public void setStatus(String st) {
        this.status = st;
    }

    @Column(name = "COMMENTS", length = 50)
    @ApiModelProperty(example = "stuff", required = true)
    @Basic(optional = false)
    public String getComments() {
        return trimField(comments);
    }

    /**
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.getOrderNumber());
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
        final Orders other = (Orders) obj;
        if (!Objects.equals(this.orderNumber, other.orderNumber)) {
            return false;
        }
        return true;
    }

    /**
     * @return the customerNumber
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ApiIgnore
    @ApiModelProperty(hidden = true)
    @JoinColumn(name = "CUSTOMERNUMBER", referencedColumnName = "CUSTOMERNUMBER", nullable = false,
            insertable = false, updatable = false)
    @JsonBackReference
    public Customers getCustomer() {
        return customer;
    }

    /**
     * @param c
     */
    public void setCustomer(Customers c) {
        this.customer = c;
    }

    /**
     * @return the orderDetails
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDERNUMBER", nullable = false)
    @JsonManagedReference
    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    /**
     * @param orderDetails the orderDetails to set
     */
    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
