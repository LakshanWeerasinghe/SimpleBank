package com.cse.database.controllers.api;

import com.cse.database.controllers.request.AuthenticationRequest;
import com.cse.database.security.MyUserDetailsService;
import com.cse.database.utill.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @GetMapping
    public String admin(){
        return ("<h1>Base Home</h1>");
    }

    @PostMapping("/authenticate")
    public ResponseEntity createAuthenticationToken(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws Exception{

        Map<String, String> body = new HashMap<>();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException ex){
            body.put("error" , "Incorrect username or password");
            return ResponseEntity.badRequest().body(body);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);


        body.put("token", jwt);

        return ResponseEntity.ok(body);

    }

}
