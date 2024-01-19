package com.example.thecommerce.ricerca;

import com.example.thecommerce.payloads.entities.RicercaDTO;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RicercaService {
    @Autowired
    RicercaRepository ricercaRepository;
    @Autowired
    UserRepository userRepository;

    public Ricerca save (RicercaDTO ricercaDTO){
        Ricerca ricerca = new Ricerca();
        ricerca.setUser(userRepository.findById(ricercaDTO.user_id()).get());
        ricerca.setTesto(ricercaDTO.ricerca());
        return ricercaRepository.save(ricerca);
    }


    public List<Ricerca> getAllByUserId(long userId){
        List<Ricerca> resultList = ricercaRepository.findByUser_Id(userId)
                .stream()
                .limit(5)
                .sorted(Comparator.comparing(Ricerca::getId).reversed())
                .collect(Collectors.toList());

        return resultList;
    }
}
