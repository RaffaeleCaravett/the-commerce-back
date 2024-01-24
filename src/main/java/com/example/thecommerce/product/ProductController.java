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
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public Page<Product> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return productService.getAll(page, size, orderBy);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('VENDITORE')")
public Product save(@RequestPart("productDTO") @Validated ProductDTO productDTO, @RequestPart("immagine_profilo") MultipartFile multipartFile, BindingResult validation) throws IOException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else{
           try{
            return productService.save(productDTO,multipartFile);
           }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
        }
    }
    @GetMapping("/category/{id}")
    public Page<Product> getAllByCategoryId(@PathVariable long id,@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "id") String orderBy){
        return productService.findByCategoryId(id,page,size,orderBy);
    }
    @GetMapping("/societa/{id}")
    public List<Product> getAllBySocietàId(@PathVariable long id){
        return productService.findBySocietàId(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('VENDITORE')")
public Product updateById(@PathVariable int id, @RequestPart("productDTO") @Validated ProductDTO body, @RequestPart(name="immagine_profilo", required = false) MultipartFile multipartFile)  {
        try{
            return productService.updateById(id,body,multipartFile);
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('VENDITORE')")
    public boolean deleteById(@PathVariable long id){
        try {
            productService.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @GetMapping("/nome/{nome}")
    public List<Product> findByNomeContaining(@PathVariable String nome){
        return productService.findByNomeContaining(nome);
    }
}
