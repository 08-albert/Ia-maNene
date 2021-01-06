package com.sda.rideshare.repositories;


import com.sda.rideshare.entities.RideEntity;
import com.sda.rideshare.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<com.sda.rideshare.entities.RideEntity,Integer> {

    List<RideEntity> getAllByDepartureCityAndArrivalCityAndDepartureDateAndAvailableSeatsGreaterThanEqualAndAvailableSeatsLessThanEqual(String departureCity, String arrivalCity, LocalDate departureDate, Integer defaultPassengerNumber, Integer passengerNumber );
    List<RideEntity> getAllByDepartureCityAndAndArrivalCityAndDepartureDate(String departureCity,String arrivalCity,LocalDate departureDate);
}
