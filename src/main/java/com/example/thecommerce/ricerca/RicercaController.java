package com.example.thecommerce.ricerca;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.RicercaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ricerca")
public class RicercaController {
    @Autowired
    RicercaService ricercaService;

    @PostMapping("")
    public Ricerca save(@RequestBody @Validated RicercaDTO ricercaDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            return ricercaService.save(ricercaDTO);
        }
    }


    @GetMapping("/user/{id}")
    public List<Ricerca> getAllByUserId(@PathVariable long id){
        return ricercaService.getAllByUserId(id);
    }
}
