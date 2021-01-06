package com.sda.rideshare.controllers;
import com.sda.rideshare.entities.*;
import com.sda.rideshare.repositories.BookingRepository;
import com.sda.rideshare.repositories.CarRepository;
import com.sda.rideshare.repositories.RideRepository;
import com.sda.rideshare.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class BookingController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;


    @PostMapping("/booking-save/{id}")
    public ModelAndView saveBooking(@ModelAttribute("booking")BookingModel bookingModel,
                                    @RequestParam (value = "bookedSeats") Integer bookedSeats,
                                    @PathVariable Integer id, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/booked-ride/{id}");
        Optional<User> user = getLoggedInUser();
        UserEntity userEntity = null;
        if (user.isPresent()) {
            String username = user.get().getUsername();
            userEntity = userRepository.getUserByUsername(username);
        }

        RideEntity rideEntity = rideRepository.findById(id).get();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingDate(localDate);
        bookingEntity.setBookingTime(localTime);
        bookingEntity.setUser(userEntity);
        bookingEntity.setRide(rideEntity);
        bookingEntity.setBookedSeats(bookedSeats);

        Integer newAvailableSeats = rideEntity.getAvailableSeats() - bookedSeats;
        rideEntity.setAvailableSeats(newAvailableSeats);
        rideRepository.save(rideEntity);

        bookingRepository.save(bookingEntity);
        return modelAndView;
    }

    @GetMapping("/booked-ride/{id}")
    public ModelAndView bookRide(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("booked-ride");
        BookingEntity bookingEntity = bookingRepository.findById(id).get();
        modelAndView.addObject("booking",bookingEntity);
        RideEntity rideEntity = bookingEntity.getRide();
        modelAndView.addObject("selectedRide",rideEntity);
        UserEntity userEntity = rideEntity.getUser();
        modelAndView.addObject("selectedDriver",userEntity);
        return modelAndView;
    }
}