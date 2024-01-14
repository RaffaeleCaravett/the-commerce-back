package com.example.thecommerce.auth;


import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.Token;
import com.example.thecommerce.payloads.entities.UserLoginDTO;
import com.example.thecommerce.payloads.entities.UserLoginSuccessDTO;
import com.example.thecommerce.payloads.entities.UserRegistrationDTO;
import com.example.thecommerce.rating.Rating;
import com.example.thecommerce.rating.RatingService;
import com.example.thecommerce.security.JWTTools;
import com.example.thecommerce.user.User;
import com.example.thecommerce.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService utenteService;
    @Autowired
    private RatingService ratingService;


    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body) throws Exception {

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveUser(@RequestBody @Validated UserRegistrationDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{token}")
    @ResponseStatus(HttpStatus.OK)
    public User verifyToken(@PathVariable String token){
            return jwtTools.verifyToken(token);
    }
    @GetMapping("/refreshToken/{refreshToken}")
    @ResponseStatus(HttpStatus.OK)
    public Token verifyRefreshToken(@PathVariable String refreshToken){
        return jwtTools.verifyRefreshToken(refreshToken);
    }


    @GetMapping("/user")
    public Page<User> getUser(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String orderBy){
        return authService.getUtenti(page, size, orderBy);
    }
    @GetMapping("/rating")
    public Page<Rating> getRatings(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy){
        return ratingService.getRatings(page, size, orderBy);
    }


}