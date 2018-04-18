/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dhenton9000.birt.jpa.domain.security;

/**
 *
 * @author dhenton
 */
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
@Table(name = "USERS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String username;
    private String login;
    private Set<Groups> groupsSet;

    public Users() {
    }

    public Users(Integer userid) {
        this.userId = userid;
    }

    @Id
    @SequenceGenerator(name="users_user_id_seq",
                       sequenceName="users_user_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="users_user_id_seq")
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false, unique = true)
    public Integer getUserid() {
        return userId;
    }

    public void setUserid(Integer userid) {
        this.userId = userid;
    }

    @Column(name = "username", length = 20)
    public String getUsername() {
        return trimField(username);
    }

    public void setUsername(String username) {
        this.username = trimField(username);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        if ((this.userId == null) ? (other.userId != null) : !this.userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Users[ userid=" + userId + " ]";
    }

    /**
     * @return the groupsSet
     */
    @ManyToMany(cascade = {
        CascadeType.ALL
    }, fetch = FetchType.LAZY)
    @JoinTable(name = "GROUP_ASSIGNMENTS", catalog = "jdatabase", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "group_id", referencedColumnName = "id")
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

    /**
     * @return the login
     */
     @Column(name = "login", length = 20)
    public String getLogin() {
        return trimField(login);
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = trimField(login);
    }

}
