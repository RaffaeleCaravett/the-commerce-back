package com.example.thecommerce.category;

import com.example.thecommerce.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Category save(@RequestBody @Validated Category category){
        try{
            return categoryService.save(category);
        }catch (Error e){
            throw new BadRequestException("Qualcosa è andato storto nel caricamento");
        }
    }
@PutMapping("/{id}")
@PreAuthorize("hasAuthority('ADMIN')")
public Category updateById(@PathVariable long id,@RequestBody Category category){
        return categoryService.updateById(id,category);
}
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean deleteById(@PathVariable long id){
        return categoryService.deleteById(id);
    }

    @PostMapping("/upload/{id}")
    public String uploadAvatar(@PathVariable long id, @RequestParam("immagine") MultipartFile body) throws IOException {

        return categoryService.uploadAvatar(id,body);
    }
}
