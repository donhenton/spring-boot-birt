package com.dhenton9000.birt.jpa.domain;

 
import static com.dhenton9000.jpa.util.EntityUtils.trimField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "products")
@ApiModel(description = "the products entity")
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT e FROM Products e"),
    @NamedQuery(name = "Products.findByid", query = "SELECT e FROM Products e WHERE e.productCode = :id")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class Products implements Serializable  {

    private ProductLines productLines;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.productCode);
        return hash;
    }

    @Override
    public String toString() {
        return "Products{" + "productName=" + productName + ", productCode=" + productCode + '}';
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
        final Products other = (Products) obj;
        if (!Objects.equals(this.productCode, other.productCode)) {
            return false;
        }
        return true;
    }

    private String productName;
    private String productCode;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private Integer quantityInStock;
    private Float buyPrice;
    private Float MSRP;

    /**
     * @return the productName
     */
    public String getProductName() {
        return trimField(productName);
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

     

    /**
     * @return the officeCode
     */
    @Id
    @Basic(optional = false)
    @Column(name = "PRODUCTCODE", nullable = false)
    public String getProductCode() {
        return trimField(productCode);
    }

    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the productScale
     */
    @Column(name = "PRODUCTSCALE", length = 50)
    @ApiModelProperty(example = "44", required = true)
    @Basic(optional = false)
    public String getProductScale() {
        return trimField(productScale);
    }

    /**
     * @param productScale the productScale to set
     */
    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    /**
     * @return the productVendor
     */
    @Column(name = "PRODUCTVENDOR", length = 50)
    @ApiModelProperty(example = "Fred's stuff", required = true)
    @Basic(optional = false)
    public String getProductVendor() {
        return trimField(productVendor);
    }

    /**
     * @param productVendor the productVendor to set
     */
    public void setProductVendor(String productVendor) {
        this.productVendor = productVendor;
    }

    /**
     * @return the productDescription
     */
    @Column(name = "PRODUCTDESCRIPTION", length = 50)
    @ApiModelProperty(example = "Fred's stuff", required = true)
    @Basic(optional = false)
    public String getProductDescription() {
        return trimField(productDescription);
    }

    /**
     * @param productDescription the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the quantityInStock
     */
    @Column(name = "QUANTITYINSTOCK", length = 50)
    @ApiModelProperty(example = "35", required = true)
    @Basic(optional = false)
    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * @param quantityInStock the quantityInStock to set
     */
    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * @return the buyPrice
     */
    @Column(name = "BUYPRICE", length = 50)
    @ApiModelProperty(example = "35.99", required = true)
    @Basic(optional = false)
    public Float getBuyPrice() {
        return buyPrice;
    }

    /**
     * @param buyPrice the buyPrice to set
     */
    public void setBuyPrice(Float buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * @return the MSRP
     */
    @Column(name = "MSRP", length = 50)
    @ApiModelProperty(example = "35.99", required = true)
    @Basic(optional = false)
    public Float getMSRP() {
        return MSRP;
    }

    /**
     * @param MSRP the MSRP to set
     */
    public void setMSRP(Float MSRP) {
        this.MSRP = MSRP;
    }

    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "PRODUCTLINE_ID", referencedColumnName = "ID", nullable = false,
            insertable = false, updatable = false)
    @JsonBackReference
    public ProductLines getProductLines() {
        return this.productLines;
    }
    
    public void setProductLines(ProductLines p)
    {
        this.productLines = p;
    }
    
    
}
