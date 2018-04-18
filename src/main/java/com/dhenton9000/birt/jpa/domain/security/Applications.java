/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.domain.security;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import static com.dhenton9000.jpa.util.EntityUtils.trimField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author dhenton
 */
@Entity
@Table(name = "APPLICATIONS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement

public class Applications implements  Serializable  {
    private static final long serialVersionUID = 1L;
        private Integer id;
        private String applicationName;
        private Set<Groups> groupsSet;

    public Applications() {
    }

    public Applications(Integer id) {
        this.id = id;
    }

    @Id
   @SequenceGenerator(name="applications_id_seq",
                       sequenceName="applications_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="applications_id_seq")
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "APPLICATION_NAME", length = 120)
    public String getApplicationName() {
        return trimField(applicationName);
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Applications other = (Applications) obj;
        if ((this.applicationName == null) ? (other.applicationName != null) : !this.applicationName.equals(other.applicationName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.applicationName != null ? this.applicationName.hashCode() : 0);
        return hash;
    }

 
    @Override
    public String toString() {
        return "Applications[ id=" + id + " ]";
    }

    /**
     * @return the groupsSet this has to be a set to allow for hydrating
     * the full set
     */
    @ManyToMany(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE
    },fetch=FetchType.LAZY)
    @JoinTable(name = "APPLICATION_GROUPS", catalog = "jdatabase", joinColumns = {
         @JoinColumn(name = "application_id")
     }, inverseJoinColumns = {
         @JoinColumn(name = "group_id")
     })
    @XmlTransient
    @JsonIgnore
    public Set<Groups> getGroupsSet() {
        return groupsSet;
    }

  

    /**
     * @param groupsSet the groupsSet to set
     */
    public void setGroupsSet(Set<Groups> groupsSet) {
        this.groupsSet = groupsSet;
    }

    
    
}