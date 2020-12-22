package com.sda.rideshare.controllers;

import com.sda.rideshare.entity.AddressEntity;
import com.sda.rideshare.entity.ModelRide;
import com.sda.rideshare.entity.RideEntity;
import com.sda.rideshare.entity.UserEntity;
import com.sda.rideshare.repository.AddressRepository;
import com.sda.rideshare.repository.RideRepository;
import com.sda.rideshare.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class RideController extends BaseController   {
    private static final Logger logger = LoggerFactory.getLogger(RideController.class);
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/rides/add")
    public ModelAndView ddCategory() {
        ModelAndView modelAndView = new ModelAndView("ride-form");

        modelAndView.addObject("modelRide", new ModelRide());
        return modelAndView;
    }
    @PostMapping("/rides/save")
    public  ModelAndView saveCategory(@ModelAttribute("modelRide") ModelRide modelRide, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/main");
        Optional<User> user = getLoggedInUser();
        UserEntity userEntity = null;
        if(user.isPresent()){
            String username = user.get().getUsername();
            userEntity = userRepository.getUserByUsername(username);
        }
        RideEntity rideEntity = new RideEntity();
        AddressEntity departureAddress = new AddressEntity();
        departureAddress.setCity(modelRide.getDepartureCity());
        departureAddress.setStreetAndNumber(modelRide.getDepartureStreetAndNumber());
        addressRepository.save(departureAddress);
        AddressEntity arrivalAddress = new AddressEntity();
        arrivalAddress.setCity(modelRide.getArrivalCity());
        arrivalAddress.setStreetAndNumber(modelRide.getArrivalStreetAndNumber());
        addressRepository.save(arrivalAddress);
        rideEntity.setRideId(modelRide.getModelRideId());
        rideEntity.setDepartureAddress(departureAddress);
        rideEntity.setArrivalAddress(arrivalAddress);

        rideEntity.setDepartureDate(modelRide.getDepartureDate());
        rideEntity.setDepartureTime(modelRide.getDepartureTime());
        rideEntity.setArrivalTime(modelRide.getArrivalTime());
        rideEntity.setPassengerNumber(modelRide.getPassengerNumber());
        rideEntity.setUser(userEntity);
        rideRepository.save(rideEntity);
        return modelAndView;
    }
    @GetMapping("/find-ride")
    public ModelAndView getMainPage () {
        ModelAndView modelAndView = new ModelAndView("find-ride");
        return modelAndView;
    }
    }


