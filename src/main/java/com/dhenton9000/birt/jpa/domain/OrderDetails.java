/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.domain;

 
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "orderdetails")
@ApiModel(description = "the orderdetails entity")
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetails implements Serializable  {

    private Integer quantityOrdered;
    private Float priceEach;
    private Integer orderLineNumber;
    private Orders orders;
    private Products product;
    private Integer pid; //fake id 

    

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDERNUMBER", referencedColumnName = "ORDERNUMBER", nullable = false,
            insertable = false, updatable = false)
    @JsonBackReference
    public Orders getOrders() {
        return this.orders;
    }

    public void setOrders(Orders n) {
        this.orders = n;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.pid);

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
        final OrderDetails other = (OrderDetails) obj;
        if (!Objects.equals(this.orders, other.orders)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

   

    /**
     * @return the productCode
     */

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "PRODUCTCODE", nullable = false)
    @JsonManagedReference
    @Basic(optional = false)
    public Products getProducts() {
        return this.product;
    }

    /**
     * @param p
     */
    public void setProducts(Products p) {
        this.product  = p;
    }

    /**
     * @return the pid
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderdetails_pid_seq")
    @SequenceGenerator(name = "orderdetails_pid_seq", sequenceName = "orderdetails_pid_seq", allocationSize = 1)

    @Column(name = "PID", nullable = false)
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * @return the quantityOrdered
     */
    @Column(name = "QUANTITYORDERED", nullable = false)
    @ApiModelProperty(example = "35", required = true)
    @Basic(optional = false)
    public Integer getQuantityOrdered() {
        return quantityOrdered;
    }

    /**
     * @param quantityOrdered the quantityOrdered to set
     */
    public void setQuantityOrdered(Integer quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    /**
     * @return the priceEach
     */
    @Column(name = "PRICEEACH", nullable = false)
    @ApiModelProperty(example = "35", required = true)
    @Basic(optional = false)
    public Float getPriceEach() {
        return priceEach;
    }

    /**
     * @param priceEach the priceEach to set
     */
    public void setPriceEach(Float priceEach) {
        this.priceEach = priceEach;
    }

    /**
     * @return the orderLineNumber
     */
    @Column(name = "ORDERLINENUMBER", nullable = false)
    @ApiModelProperty(example = "35", required = true)
    @Basic(optional = false)
    public Integer getOrderLineNumber() {
        return orderLineNumber;
    }

    /**
     * @param orderLineNumber the orderLineNumber to set
     */
    public void setOrderLineNumber(Integer orderLineNumber) {
        this.orderLineNumber = orderLineNumber;
    }

}
