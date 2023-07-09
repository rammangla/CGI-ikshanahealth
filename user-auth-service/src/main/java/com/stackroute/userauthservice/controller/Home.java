package com.stackroute.userauthservice.controller;


import com.stackroute.userauthservice.JwtUtility.JwtUtil;
import com.stackroute.userauthservice.model.AuthenticationRequest;
import com.stackroute.userauthservice.model.AuthenticationResponse;
import com.stackroute.userauthservice.model.User;
import com.stackroute.userauthservice.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class Home {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailService userDetailService;
    @Autowired
    private JwtUtil jwtTokenUtil;

   @GetMapping("/welcome")
    public String home(){
        String text="This page is for authorized user";
        return text;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
       }catch (BadCredentialsException e){
           throw new Exception("Incorrect Username or Password",e);
       }
       final UserDetails userDetails=userDetailService.loadUserByUsername(authenticationRequest.getUsername());

       final String jwt=jwtTokenUtil.generateToken(userDetails);
       return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

