package com.sda.rideshare.entity;



import javax.persistence.*;

@Entity
@Table(name = "authorities")

public class AuthorityEntity {
    @Id
    @Column(length = 50)
    private Integer userId;

    private String authority;
    @ManyToOne
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    private UserEntity user;

    public AuthorityEntity() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authorities) {
        this.authority = authorities;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
