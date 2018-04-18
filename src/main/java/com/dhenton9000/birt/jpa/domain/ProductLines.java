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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "productlines")
@ApiModel(description = "the productline entity")
@NamedQueries({
    @NamedQuery(name = "ProductLines.findAll", query = "SELECT e FROM ProductLines e"),
    @NamedQuery(name = "ProductLines.findByid", query = "SELECT e FROM ProductLines e WHERE e.id = :id")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class ProductLines implements Serializable  {

    private Integer id;
    private String description;
    private Set<Products> products;

    

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productlines_id_seq")
    @SequenceGenerator(name = "productlines_id_seq", sequenceName = "productlines_id_seq", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    @ApiModelProperty(example = "1", required = true)
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    @Column(name = "DESCRIPTION", length = 150)
    @ApiModelProperty(example = "Stuff", required = true)
    public String getDescription() {
        return trimField(description);
    }

    /**
     * @param Description the description to set
     */
    public void setDescription(String Description) {
        this.description = Description;
    }

    @Override
    public String toString() {
        return "ProductLines{" + "id=" + id + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final ProductLines other = (ProductLines) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID", nullable = false)
    @JsonManagedReference
    public Set<Products> getProducts() {
 
        return this.products;
    }

    /**
     * @param products
     * @param employees the employees to set
     */
    public void setProducts(Set<Products> products) {
        this.products = products;
    }
    
    
}
