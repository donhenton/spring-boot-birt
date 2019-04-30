 

package com.dhenton9000.birt.jpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.xml.bind.annotation.XmlRootElement;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
public class OfficeExplorer extends Offices {
    
    private Float salesTotal = 0.0f;

    /**
     * @return the salesTotal
     */
    public Float getSalesTotal() {
        return salesTotal;
    }

    /**
     * @param salesTotal the salesTotal to set
     */
    public void setSalesTotal(Float salesTotal) {
        this.salesTotal = salesTotal;
    }
    
    

}
