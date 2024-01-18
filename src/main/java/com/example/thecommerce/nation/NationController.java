package com.example.thecommerce.nation;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nation")

public class NationController {
    @Autowired
    private NationService nationService;

    @GetMapping("")
    public List<Nation> getNations() {
        return  nationService.getNations();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
public Nation save(@RequestBody @Validated Nation nation, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return nationService.save(nation);
        }
    }

    @GetMapping(value = "/{id}")
    public Nation findById(@PathVariable int id) {
        return  nationService.findById(id);
    }

    @GetMapping("/cities/{id}")
    public Nation findNationsByCityId(@PathVariable int id) {
        return nationService.findNationsByCityId(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable int id) throws NotFoundException {
        nationService.findByIdAndDelete(id);
    }
}
