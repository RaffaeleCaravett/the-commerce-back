package com.example.thecommerce.auth;

import com.example.thecommerce.city.CityRepository;
import com.example.thecommerce.enums.UserRoles;
import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.exception.UnauthorizedException;
import com.example.thecommerce.nation.NationRepository;
import com.example.thecommerce.payloads.entities.Token;
import com.example.thecommerce.payloads.entities.UserLoginDTO;
import com.example.thecommerce.payloads.entities.UserRegistrationDTO;
import com.example.thecommerce.security.JWTTools;
import com.example.thecommerce.user.User;
import com.example.thecommerce.user.UserRepository;
import com.example.thecommerce.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private NationRepository nationRepository;
    @Autowired
    private CityRepository cityRepository;

    public Token authenticateUser(UserLoginDTO body) throws Exception {
        // 1. Verifichiamo che l'email dell'utente sia nel db
        User user = usersService.findByEmail(body.email());
        // 2. In caso affermativo, verifichiamo se la password corrisponde a quella trovata nel db
        if(bcrypt.matches(body.password(), user.getPassword()))  {
            // 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }


    public User registerUser(UserRegistrationDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        userRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        User newUser = new User();
        newUser.setNomeCompleto(body.nomeCompleto());
        newUser.setEtà(body.età());
        newUser.setEmail(body.email());
        newUser.setNazione(nationRepository.findById(body.nazione()).get());
        newUser.setCitta(cityRepository.findById(body.citta()).get());
        //found.setPassword(bcrypt.encode(body.getPassword()));
        newUser.setRole(UserRoles.UTENTE);
        userRepository.save(newUser);


        return newUser;
    }
    public Page<User> getUtenti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return userRepository.findAll(pageable);
    }
}