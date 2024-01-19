package com.example.thecommerce.bozza;


import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.BozzaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bozza")
public class BozzaController {

    @Autowired
    BozzaService bozzaService;

    @PostMapping("")
    public Bozza save(@RequestBody @Validated BozzaDTO bozzaDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return bozzaService.save(bozzaDTO);
        }
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id){
        return bozzaService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Bozza updateById(@PathVariable long id, @RequestBody BozzaDTO bozzaDTO){
        return bozzaService.updateById(id,bozzaDTO);
    }

    @GetMapping("/user/{id}")
    public List<Bozza> getByUserId(@PathVariable long id){
        return bozzaService.getAllByUserId(id);
    }

    @GetMapping("/{id}")
    public Bozza getById(@PathVariable long id){
        return bozzaService.getById(id);
    }
}

