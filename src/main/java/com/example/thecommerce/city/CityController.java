package com.example.thecommerce.city;

import com.example.thecommerce.category.Category;
import com.example.thecommerce.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    CityService cityService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public City save(@RequestBody @Validated City city, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return cityService.save(city);
        }
    }

    @GetMapping("")
    public List<City> getAll(){
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public City getById(@PathVariable long id){
        return  cityService.findById(id);
    }

    @GetMapping("/nationId/{id}")
    public List<City> getByNationId(@PathVariable long id){
        return cityService.getByNationId(id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
public boolean deleteById(@PathVariable long id){
        return cityService.deleteById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
public City updateById(@PathVariable long id, @RequestBody City city){
        return cityService.updateById(id,city);
    }
}
