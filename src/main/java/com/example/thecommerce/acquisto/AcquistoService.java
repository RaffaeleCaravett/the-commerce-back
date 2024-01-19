package com.example.thecommerce.acquisto;

import com.example.thecommerce.payloads.entities.AcquistoDTO;
import com.example.thecommerce.product.Product;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.User;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcquistoService {

    @Autowired
    AcquistoRepository acquistoRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public Acquisto save(AcquistoDTO acquistoDTO){
       Acquisto acquisto= new Acquisto();
       acquisto.setUser(userRepository.findById(acquistoDTO.user_id()).get());
        List<Product> products= new ArrayList<>();
        for (Long l : acquistoDTO.product_id()){
            products.add(productRepository.findById(l).get());
        }
        acquisto.setProductList(products);
        double totale = 0;
        for(Product p : acquisto.productList){
            totale+= p.getPrezzo();
        }
        acquisto.setTotale(totale);
            return acquistoRepository.save(acquisto);
    }

    public boolean deleteById(long id){
        try {
            acquistoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<Acquisto> getAllByUserId(long id){
        return acquistoRepository.findByUser_Id(id);
    }

    public Acquisto updateById(long id, AcquistoDTO acquistoDTO){
        Acquisto acquisto= acquistoRepository.findById(id).get();
        List<Product> products= new ArrayList<>();
        for (Long l : acquistoDTO.product_id()){
            products.add(productRepository.findById(l).get());
        }
        acquisto.setProductList(products);
        double totale = 0;
        for(Product p : acquisto.productList){
            totale+= p.getPrezzo();
        }
        acquisto.setTotale(totale);
        return acquistoRepository.save(acquisto);
    }

    public Acquisto getById(long id) {
        return acquistoRepository.findById(id).get();
    }
}
