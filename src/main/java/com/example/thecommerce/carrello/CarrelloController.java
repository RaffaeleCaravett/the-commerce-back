package com.example.thecommerce.carrello;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.CarrelloDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {

    @Autowired
    CarrelloService carrelloService;


    @PostMapping("")
    public Carrello save (@RequestBody @Validated CarrelloDTO carrelloDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
       return carrelloService.save(carrelloDTO);
    }

    @GetMapping("/user/{id}")
    public Carrello getByUserId(@PathVariable long id){
        return carrelloService.findByUserId(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id){
        return carrelloService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Carrello findByIdAndUpdate(@PathVariable long id, @RequestBody CarrelloDTO carrelloDTO){
        return carrelloService.updateById(id,carrelloDTO);
    }
    @GetMapping("/svuota/{id}")
    public Carrello svuota(@PathVariable long id){
        return carrelloService.svuotaCarrello(id);
    }
}
