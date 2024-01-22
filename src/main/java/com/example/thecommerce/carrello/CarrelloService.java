package com.example.thecommerce.carrello;

import com.example.thecommerce.exception.BadRequestException;
import com.example.thecommerce.payloads.entities.CarrelloDTO;
import com.example.thecommerce.product.Product;
import com.example.thecommerce.product.ProductRepository;
import com.example.thecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    CarrelloRepository carrelloRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    public Carrello save(CarrelloDTO carrelloDTO){
        List<Carrello> carrello = carrelloRepository.findAll();
        for(Carrello c :carrello){
            if(c.getUser().getId()==carrelloDTO.user_id()){
                throw new BadRequestException("Hai già un carrello attivo.");
            }
        }
        Carrello carrello1 = new Carrello();
        carrello1.setUser(userRepository.findById(carrelloDTO.user_id()).get());
        List<Product> products = new ArrayList<>();
        for(Long l : carrelloDTO.products_id()){
            products.add(productRepository.findById(l).get());
        }
        carrello1.setProducts(products);
        double totale=0;
    for(Product p : carrello1.getProducts()){
       totale+=p.getPrezzo();
    }
    carrello1.setTotale(totale);
    return carrelloRepository.save(carrello1);
    }

    public boolean deleteById(long id){
        try {
            carrelloRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

public Carrello updateById(long id, CarrelloDTO carrelloDTO){
        Carrello carrello= carrelloRepository.findById(id).get();

        List<Product> products= new ArrayList<>();
        for(Long l: carrelloDTO.products_id()){
            products.add(productRepository.findById(l).get());
        }
        carrello.setProducts(products);
return carrelloRepository.save(carrello);
}

public Carrello findByUserId(long id){
        return carrelloRepository.findByUser_Id(id);
}

public Carrello svuotaCarrello(long id){
        Carrello carrello= carrelloRepository.findById(id).get();
        carrello.setProducts(new ArrayList<>());
        return carrelloRepository.save(carrello);
}

}
