package com.zorginstellingen.controller;

import com.zorginstellingen.dto.UserDto;
import com.zorginstellingen.model.User;
import com.zorginstellingen.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@CrossOrigin(value = {"http://localhost:3000"})
public class LoginController {
    @Autowired
    private UserService userService;

    @Value("${base.url.login}")
    private String baseUrlLogin;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(
            @RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "password", required = true) String password) {

        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        String url = baseUrlLogin + "login";
        try {
            HttpEntity<String> response = restTemplate.postForEntity(url, map, String.class);
            User user = userService.getUserByUserName(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
            }
            user.setToken(response.getBody().toString());
            return ResponseEntity.ok(Collections.singleton(user));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody UserDto userDto){
        try{
            return ResponseEntity.ok(userService.register(userDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
