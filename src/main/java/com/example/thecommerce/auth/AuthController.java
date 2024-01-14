package com.example.thecommerce.auth;


import com.example.thecommerce.payloads.entities.Token;
import com.example.thecommerce.payloads.entities.UserLoginDTO;
import com.example.thecommerce.payloads.entities.UserLoginSuccessDTO;
import com.example.thecommerce.payloads.entities.UserRegistrationDTO;
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
    @GetMapping("/search")
    public Page<User> searchByParams(@RequestParam(required = false) String nome,
                                     @RequestParam(required = false) String cognome,
                                     @RequestParam(required = false) String nazione,
                                     @RequestParam(required = false) String continent,
                                     @RequestParam(required = false) String email,
                                     @RequestParam(required = false) int eta,
                                     @RequestParam(defaultValue = "ASC") String direction,
                                     @RequestParam(defaultValue = "id") String sort,
                                     Pageable pageable) {

        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        Pageable modifiedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortDirection, sort);

        Page<User> result = authService.findByDynamicParams(nome, cognome, nazione, continent, email, eta, modifiedPageable);
        return result;
    }
}