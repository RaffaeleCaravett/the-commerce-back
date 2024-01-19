package com.example.thecommerce.acquisto;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.AcquistoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acquisto")
public class AcquistoController {

    @Autowired
    AcquistoService acquistoService;

    @PostMapping("")
public Acquisto save(@RequestBody @Validated AcquistoDTO acquistoDTO, BindingResult validation){
    if(validation.hasErrors()){
        throw new BadRequestException(validation.getAllErrors());
    }else{
        return acquistoService.save(acquistoDTO);
    }
}

@DeleteMapping("/{id}")
public boolean deleteById(@PathVariable long id){
        return acquistoService.deleteById(id);
}

@PutMapping("/{id}")
    public Acquisto updateById(@PathVariable long id, @RequestBody AcquistoDTO acquistoDTO){
        return acquistoService.updateById(id,acquistoDTO);
}

@GetMapping("/user/{id}")
public List<Acquisto> getByUserId(@PathVariable long id){
        return acquistoService.getAllByUserId(id);
}

  @GetMapping("/{id}")
    public Acquisto getById(@PathVariable long id){
        return acquistoService.getById(id);
  }
}
