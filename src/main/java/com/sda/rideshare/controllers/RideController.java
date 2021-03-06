package com.sda.rideshare.controllers;
import com.sda.rideshare.entities.*;
import com.sda.rideshare.repositories.BookingRepository;
import com.sda.rideshare.repositories.CarRepository;
import com.sda.rideshare.repositories.RideRepository;
import com.sda.rideshare.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class RideController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(RideController.class);
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/rides/add")
    public ModelAndView addRide() {
        ModelAndView modelAndView = new ModelAndView("ride-form");
        Optional<User> user = getLoggedInUser();
        Integer id = null;
        if (user.isPresent()) {
            String username = user.get().getUsername();
            UserEntity userEntity = userRepository.getUserByUsername(username);
            id = userEntity.getUserId();
        }
        modelAndView.addObject("user", userRepository.findById(id).get());
        modelAndView.addObject("modelRide", new RideEntity());
        return modelAndView;
    }

    @PostMapping("/rides/save")
    public ModelAndView saveRide( @Valid @ModelAttribute("modelRide") RideEntity rideEntity, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/main");

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName("ride-form");
            Optional<User> user = getLoggedInUser();
            UserEntity userEntity = null;
            Integer id = null;
            if (user.isPresent()) {
                String username = user.get().getUsername();
                userEntity = userRepository.getUserByUsername(username);
                id = userEntity.getUserId();
            }
            rideEntity.setAvailableSeats(rideEntity.getPassengerNumber());
            modelAndView.addObject("user", userRepository.findById(id).get());
            modelAndView.addObject("modelRide", rideEntity);
            return modelAndView;
        }

        Optional<User> user = getLoggedInUser();
        UserEntity userEntity = null;
        if (user.isPresent()) {
            String username = user.get().getUsername();
            userEntity = userRepository.getUserByUsername(username);
        }

        rideEntity.setAvailableSeats(rideEntity.getPassengerNumber());
        rideEntity.setUser(userEntity);
        rideRepository.save(rideEntity);
        return modelAndView;
    }

    @GetMapping("/find-ride")
    public ModelAndView getRides() {
        ModelAndView modelAndView = new ModelAndView("find-ride");
        return modelAndView;
    }

    @GetMapping("/found-rides")
    public ModelAndView getFoundRides(@RequestParam(value = "departureCity") String departureCity,
                                      @RequestParam(value = "arrivalCity") String arrivalCity,
                                      @RequestParam(value = "departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate){
////                                      @RequestParam(value = "passengerNumber")Integer bookedSeats) {
        ModelAndView modelAndView = new ModelAndView("found-rides");
//        List<RideEntity> rides = rideRepository.getAllByDepartureCityAndAndArrivalCityAndDepartureDate(departureCity,arrivalCity,departureDate);
//        Integer defaultavailableSeats = 1;
//        if(rides!= null) {
//            for (RideEntity r : rides) {
//                defaultavailableSeats = r.getAvailableSeats();
//                modelAndView.addObject("foundRides", rideRepository.getAllByDepartureCityAndArrivalCityAndDepartureDateAndAvailableSeatsGreaterThanEqualAndAvailableSeatsLessThanEqual(departureCity, arrivalCity, departureDate,bookedSeats,defaultavailableSeats));
//                return modelAndView;
//            }
//        }
        List<RideEntity>rides=rideRepository.getAllByDepartureCityAndAndArrivalCityAndDepartureDate(departureCity,arrivalCity,departureDate);
        modelAndView.addObject("foundRides", rides);
        rides.removeIf(ride -> ride.getAvailableSeats() <= 0);
        return modelAndView;
    }

    @GetMapping("/my-rides")
    public ModelAndView getHistoryRides() {
        ModelAndView modelAndView = new ModelAndView("rides");
        Optional<User> user = getLoggedInUser();
        Integer id = null;
        if (user.isPresent()) {
            String username = user.get().getUsername();
            UserEntity userEntity = userRepository.getUserByUsername(username);
            id = userEntity.getUserId();
        }
        modelAndView.addObject("user", userRepository.findById(id).get());
        return modelAndView;
    }
    @GetMapping("/select-ride/{id}")
    public ModelAndView selectRides(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("selected-ride");
        RideEntity rideEntity= rideRepository.findById(id).get();
        modelAndView.addObject("selectedRide", rideRepository.findById(id).get());
        UserEntity userEntity = rideEntity.getUser();
        Integer userId = userEntity.getUserId();
        modelAndView.addObject("selectedDriver",userRepository.findById(userId).get());
        CarEntity carEntity = rideEntity.getCarEntity();
        Integer carId = carEntity.getCarId();
        modelAndView.addObject("selectedCar",carRepository.findById(carId).get());
        modelAndView.addObject("booking",new BookingModel());
        return modelAndView;
    }

//    @GetMapping("/delete-ride/{id}")
//    public ModelAndView deleteRides(@PathVariable Integer id) {
//        ModelAndView modelAndView = new ModelAndView("redirect:/view-rides");
//        rideRepository.deleteById(id);
//        return modelAndView;
//    }

    @GetMapping("/delete-ride/{id}")
    public ModelAndView deleteRide(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/my-rides");
        RideEntity rideEntity = rideRepository.findById(id).get();
        List<BookingEntity> bookingList = rideEntity.getBookingList();
        bookingRepository.deleteAll(bookingList);
        rideRepository.deleteById(id);
        return modelAndView;
    }
}