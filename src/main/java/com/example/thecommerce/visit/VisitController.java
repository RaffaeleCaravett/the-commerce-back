package com.example.thecommerce.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/visit")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitController {

    @Autowired VisitService visitService;

    @GetMapping("")
    public long findAll(){
        return visitService.getAll();
    }
    @GetMapping("/{date}")
    public long getByDate(@PathVariable LocalDate date){
        return visitService.getByDate(date);
    }

    @PostMapping("")
    public void save(Object body){
        visitService.save();
    }
}
