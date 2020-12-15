package com.sda.rideshare.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class RideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rideId;
    private LocalDateTime departureDateAndTime;
    private LocalDateTime arrivalDateAndTime;
    private Integer passengerNumber;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name ="addressIdDepartures")
    private AddressEntity departureAddress;

    @OneToOne
    @JoinColumn(name ="addressIdArrival")
    private AddressEntity arrivalAddress;

    public AddressEntity getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(AddressEntity departureAddress) {
        this.departureAddress = departureAddress;
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public LocalDateTime getDepartureDateAndTime() {
        return departureDateAndTime;
    }

    public void setDepartureDateAndTime(LocalDateTime departureDateAndTime) {
        this.departureDateAndTime = departureDateAndTime;
    }

    public LocalDateTime getArrivalDateAndTime() {
        return arrivalDateAndTime;
    }

    public void setArrivalDateAndTime(LocalDateTime arrivalDateAndTime) {
        this.arrivalDateAndTime = arrivalDateAndTime;
    }

    public Integer getPassengerNumber() {
        return passengerNumber;
    }

    public void setPassengerNumber(Integer passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public AddressEntity getArrivalAddress() {
        return arrivalAddress;
    }

    public void setArrivalAddress(AddressEntity arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
    }
}
