package com.dhenton9000.birt.jpa.domain.security.dto;

import com.dhenton9000.birt.jpa.domain.security.Applications;
import com.dhenton9000.birt.jpa.domain.security.Groups;
import com.dhenton9000.birt.jpa.domain.security.Users;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlRootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement
public class GroupDTO {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(GroupDTO.class);

    private final Integer id;
    private final String groupName;
    private final Set<Users> usersSet;
    private final Set<Applications> applicationsSet;
    private List<Applications> wholeApplicationsList;
    private List<Users> wholeUsersList;

    public GroupDTO(Groups g) {
        this.id = g.getId();
        this.groupName = g.getGroupName();
        this.applicationsSet = new HashSet<>();
        this.usersSet = new HashSet<>();
        g.getApplicationsSet().forEach(s -> {
            Applications a = new Applications();
            a.setApplicationName(s.getApplicationName());
            a.setId(s.getId());
            this.applicationsSet.add(a);
        });
        g.getUsersSet().forEach(user -> {
            Users u = new Users();
            u.setLogin(user.getLogin());
            u.setUserid(user.getUserid());
            u.setUsername(user.getUsername());
            this.usersSet.add(u);
           
            

        });
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @return the usersSet
     */
    public Set<Users> getUsers() {
        return usersSet;
    }

    /**
     * @return the applicationsSet
     */
    public Set<Applications> getApplications() {
        return applicationsSet;
    }

    public void setWholeApplicationList(List<Applications> apps) {
       this.wholeApplicationsList = apps;
    }
    public void setWholeUserList(List<Users> userList) {
       this.wholeUsersList = userList;
    }
    
    public List<Applications> getApplicationsNotInGroup() {
        return 
        this.wholeApplicationsList.stream().filter(w -> {
          return !this.applicationsSet.contains(w);
        }).collect(Collectors.toList());
        
    }
    
    public List<Users> getUsersNotInGroup() {
        return 
        this.wholeUsersList.stream().filter(w -> {
          return !this.usersSet.contains(w);
        }).collect(Collectors.toList());
        
    }

}
