package com.example.batch7.ch8.controller.oauth;

import com.example.batch7.ch8.repository.oauth.UserRepository;
import com.example.batch7.ch8.service.oauth.Oauth2UserDetailsService;
import com.example.batch7.ch8.service.oauth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    private Oauth2UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

//    @GetMapping("/detail-profile")
//    public ResponseEntity<Map> detailProfile(
//            Principal principal
//    ) {
//        Map map = userService.getDetailProfile(principal);
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }
}