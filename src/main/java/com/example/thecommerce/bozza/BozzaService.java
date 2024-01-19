package com.example.thecommerce.bozza;

import com.example.thecommerce.acquisto.Acquisto;
import com.example.thecommerce.payloads.entities.AcquistoDTO;
import com.example.thecommerce.payloads.entities.BozzaDTO;
import com.example.thecommerce.product.Product;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BozzaService {
    @Autowired
    BozzaRepository bozzaRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public Bozza save(BozzaDTO bozzaDTO){
        Bozza bozza =new Bozza();
        bozza.setCreated_at(LocalDate.now());
        bozza.setUser(userRepository.findById(bozzaDTO.user_id()).get());
        List<Product> products =new ArrayList<>();
        for(Long l : bozzaDTO.product_id()){
            products.add(productRepository.findById(l).get());
        }
        bozza.setProducts(products);
        return bozzaRepository.save(bozza);
    }

    public boolean deleteById(long id){
        try {
            bozzaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<Bozza> getAllByUserId(long id){
        return bozzaRepository.findByUser_Id(id);
    }

    public Bozza updateById(long id, BozzaDTO bozzaDTO){
        Bozza bozza= bozzaRepository.findById(id).get();
        List<Product> products= new ArrayList<>();
        for (Long l : bozzaDTO.product_id()){
            products.add(productRepository.findById(l).get());
        }
        bozza.setProducts(products);
        return bozzaRepository.save(bozza);
    }

    public Bozza getById(long id) {
        return bozzaRepository.findById(id).get();
    }

}
