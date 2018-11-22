package com.netcracker.edu.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Account {
    private long id;
    private String name;
    private String sname;
    private String email;
    private Long userId;
    //private User user;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sname", nullable = true, length = 45)
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(name, account.name) &&
                Objects.equals(sname, account.sname) &&
                Objects.equals(email, account.email) &&
                Objects.equals(userId, account.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sname, email, userId);
    }

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
