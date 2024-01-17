package com.example.thecommerce.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitService {

    @Autowired VisitRepository visitRepository;

    public void save(){
        Visit visit = new Visit();
        visit.setCreated(LocalDate.now());
        visitRepository.save(visit);
    }

    public long getAll(){

        List<Visit> visits=visitRepository.findAll();
    return visits.size();
    }

    public long getByDate(LocalDate date){

        List<Visit> visits =visitRepository.findByCreated(date);
        return visits.size();
    }
}
