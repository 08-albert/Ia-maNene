package com.sda.rideshare.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "rides")
public class RideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rideId;
    private String departureCity;
    private String departureStreetAndNumber;
    private String arrivalCity;
    private String arrivalStreetAndNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime departureTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime arrivalTime;
    private Integer passengerNumber;
    private Integer availableSeats;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="carId")
    private CarEntity carEntity;

    @OneToMany(mappedBy = "ride")
    private List<BookingEntity>bookingList;

    public List<BookingEntity> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingEntity> bookingList) {
        this.bookingList = bookingList;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public CarEntity getCarEntity() {
        return carEntity;
    }

    public void setCarEntity(CarEntity carEntity) {
        this.carEntity = carEntity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCit) {
        this.departureCity = departureCit;
    }

    public String getDepartureStreetAndNumber() {
        return departureStreetAndNumber;
    }

    public void setDepartureStreetAndNumber(String streetAndNumber) {
        this.departureStreetAndNumber = streetAndNumber;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalStreetAndNumber() {
        return arrivalStreetAndNumber;
    }

    public void setArrivalStreetAndNumber(String arrivalStreetAndNumber) {
        this.arrivalStreetAndNumber = arrivalStreetAndNumber;
    }

    //    @OneToOne
//    @JoinColumn(name ="cityDeparture" ,referencedColumnName = "city")
//    private AddressEntity departureAddress;
//
//
//    @OneToOne
//    @JoinColumn(name ="cityArrival",referencedColumnName = "city")
//    private AddressEntity arrivalAddress;

    public RideEntity() {
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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


}
