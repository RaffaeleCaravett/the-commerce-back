package com.example.thecommerce.product;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('UTENTE,VENDITORE')")
    public Page<Product> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return productService.getAll(page, size, orderBy);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('VENDITORE')")
public Product save(@RequestPart("productDTO") @Validated ProductDTO productDTO, @RequestPart("immagine_profilo") MultipartFile multipartFile, BindingResult validation) throws IOException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
            return productService.save(productDTO,multipartFile);
        }
    }
    @GetMapping("/category/{id}")
    @PreAuthorize("hasAnyAuthority('UTENTE,VENDITORE')")
    public Page<Product> getAllByCategoryId(@PathVariable long id,@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id") String orderBy){
        return productService.findByCategoryId(id,page,size,orderBy);
    }
    @GetMapping("/societa/{id}")
    @PreAuthorize("hasAnyAuthority('UTENTE,VENDITORE')")
    public Page<Product> getAllBySocietàId(@PathVariable long id,@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return productService.findBySocietàId(id,page,size,orderBy);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('VENDITORE')")
public Product updateById(@PathVariable int id, @RequestBody ProductDTO body,@RequestParam("immagine_profilo") MultipartFile multipartFile) throws IOException {
        return productService.updateById(id,body,multipartFile);
    }
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable long id){
        try {
            productService.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
