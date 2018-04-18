/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.domain.security;

import static com.dhenton9000.jpa.util.EntityUtils.trimField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dhenton
 */
@Entity
@Table(name = "GROUPS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement

public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String groupName;

   
    
    @JsonIgnore
    private Set<Users> usersSet;
    
    
    
    @JsonIgnore
    private Set<Applications> applicationsSet;

    public Groups() {
    }

    public Groups(Integer id) {
        this.id = id;
    }
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="groups_id_seq")
    @Column(name = "id", nullable = false,unique=true)
    @SequenceGenerator(name="groups_id_seq",
                       sequenceName="groups_id_seq",
                       allocationSize=1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "GROUP_NAME", length = 120)
    public String getGroupName() {
        return trimField(groupName);
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Groups other = (Groups) obj;
        if ((this.groupName == null) ? (other.groupName != null) : !this.groupName.trim().equals(other.groupName.trim())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.groupName != null ? this.groupName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Groups[ id=" + id + " ]";
    }

    /**
     * @return the usersSet
     */
    @ManyToMany(cascade = {
        CascadeType.ALL
    }, fetch = FetchType.LAZY)
    @JoinTable(name = "GROUP_ASSIGNMENTS", catalog = "jdatabase", joinColumns = {
        @JoinColumn(name = "group_id",referencedColumnName="id", nullable= false, updatable = false)
    }, inverseJoinColumns = {
        @JoinColumn(name = "user_id",referencedColumnName="user_id", nullable= false, updatable = false)
    })
    @XmlTransient
    public Set<Users> getUsersSet() {
        return usersSet;
    }

    /**
     * @param usersSet the usersSet to set
     */
    public void setUsersSet(Set<Users> usersSet) {
        this.usersSet = usersSet;
    }

    /**
     * @return the applicationsSet
     */
    @ManyToMany(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinTable(name = "APPLICATION_GROUPS", catalog = "jdatabase", joinColumns = {
        @JoinColumn(name = "group_id", nullable= false, updatable = false)
    }, inverseJoinColumns = {
        @JoinColumn(name = "application_id", nullable= false, updatable = false)
    })
    @XmlTransient
    @JsonIgnore
    public Set<Applications> getApplicationsSet() {
        return applicationsSet;
    }

    /**
     * @param applicationsSet the applicationsSet to set
     */
    public void setApplicationsSet(Set<Applications> applicationsSet) {
        this.applicationsSet = applicationsSet;
    }

}
