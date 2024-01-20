package com.example.thecommerce.società;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.SocietàDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/societa")
public class SocietàContoller {
    @Autowired
    SocietàService societàService;

    @PostMapping("")
    public Società save(@RequestBody @Validated SocietàDTO societàDTO, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return societàService.save(societàDTO);
        }
    }
    @GetMapping("")
    public Page<Società> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy) {
        return societàService.getSocietà(page, size, orderBy);
    }
    @GetMapping("/{schedaAnagraficaId}")
    public Società getFromSchedaAnagraficaId(@PathVariable long schedaAnagraficaId) {
        return societàService.getSocietàBySchedaAnagraficaId(schedaAnagraficaId);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id) {
        return societàService.deleteById(id);
    }
    @PutMapping("/{id}")
    public Società updateById(@PathVariable long id,@RequestBody SocietàDTO societàDTO) {
        return societàService.updateById(id,societàDTO);
    }
}
