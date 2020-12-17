package com.sda.rideshare.controllers;
import com.sda.rideshare.entity.AuthorityEntity;
import com.sda.rideshare.entity.UserEntity;
import com.sda.rideshare.repository.AuthorityRepository;
import com.sda.rideshare.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@RestController
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;

    @GetMapping("/register")
    public ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new UserEntity());
        modelAndView.addObject("editMode", false);
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView registerUserRequest( @ModelAttribute("user") UserEntity userEntity) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
//        if(bindingResult.hasErrors()){
//            modelAndView.setViewName("register");
//            modelAndView.addObject("user", userEntity);
//            modelAndView.addObject("editMode",  false);
//            return modelAndView;
//        }
//        Optional<UserEntity> optionalUserEntity = userRepository.findById(userEntity.getUserId());
//        if (optionalUserEntity.isPresent()) {
//            UserEntity editedUserEntity = optionalUserEntity.get();
//            editedUserEntity.setPassword(userEntity.getPassword());
////            editedUserEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//            userEntity = editedUserEntity;
//        } else {
            userEntity.setEnabled(true);
//            userEntity.setPassword(userEntity.getPassword());
////            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//        }
        userEntity = userRepository.save(userEntity);
        if (null == userEntity.getAuthorities()) {
            AuthorityEntity authorityEntity = new AuthorityEntity();
            authorityEntity.setUserId(userEntity.getUserId());
            authorityEntity.setAuthority("USER");
            authorityRepository.save(authorityEntity);
        }
        return modelAndView;
    }
    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
}








