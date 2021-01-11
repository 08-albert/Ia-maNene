package com.sda.rideshare.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Email
    private String email;

    @Pattern(regexp="^\\p{L}+(?: \\p{L}+)*$",message = "Date incorecte")
    @NotBlank(message = "Campul nu poate fi gol!")
    @Size(min = 3,max = 10,message = "Camp Invalid")
    private String name;

    @Past(message = "Data introdusa este incorecta")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    private String username;
    private String password;

    @NotNull(message = "Campul nu poate fi gol")
    @Pattern(regexp = "[0-9]+",message = "nr invalid")
    @Size(min = 10,max = 13,message = "Camp Invalid")
    private String phoneNumber;
    private Boolean enabled;

    @Pattern(regexp="^\\p{L}+(?: \\p{L}+)*$",message = "Date incorecte")
    @NotBlank(message = "Campul nu poate fi gol!")
    @Size(min = 3,max = 10,message = "Camp Invalid")
    private String surname;


    @OneToMany(mappedBy = "user")
    private List<AuthorityEntity> authorities;

    @OneToMany(mappedBy = "user")
    private List<RideEntity> rides;

    @OneToMany(mappedBy = "user")
    private List<CarEntity> cars;

    @OneToMany(mappedBy = "user")
    private List<BookingEntity>bookingList;

    public List<BookingEntity> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingEntity> bookingList) {
        this.bookingList = bookingList;
    }

    public UserEntity() {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<AuthorityEntity> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    public List<RideEntity> getRides() {
        return rides;
    }

    public void setRides(List<RideEntity> rides) {
        this.rides = rides;
    }

    public List<CarEntity> getCars() {
        return cars;
    }

    public void setCars(List<CarEntity> cars) {
        this.cars = cars;
    }
}


