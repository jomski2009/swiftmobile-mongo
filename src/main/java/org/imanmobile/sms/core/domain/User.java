package org.imanmobile.sms.core.domain;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(value = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -9206497623787633942L;

    @Id
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private long cellnumber;
    private Date datejoined;
    private boolean active;
    private String password;
    private Account account;
    private int role;

    @Reference(lazy = true)
    private List<Group> usergroups = new ArrayList<>();

    public User() {
    }

    private User(String username, String firstname, String lastname, String email, long cellnumber, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.cellnumber = cellnumber;
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCellnumber() {
        return cellnumber;
    }

    public void setCellnumber(long cellnumber) {
        this.cellnumber = cellnumber;
    }

    public Date getDatejoined() {
        return datejoined;
    }

    public void setDatejoined(Date datejoined) {
        this.datejoined = datejoined;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Group> getUsergroups() {
        return usergroups;
    }

    public void setUsergroups(List<Group> usergroups) {
        this.usergroups = usergroups;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", firstname="
                + firstname + ", lastname=" + lastname + ", email=" + email
                + ", cellnumber=" + cellnumber + "]";
    }


}
