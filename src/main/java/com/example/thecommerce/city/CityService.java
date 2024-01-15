package com.example.thecommerce.city;

import com.example.thecommerce.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;


    public City save(City city){
        return cityRepository.save(city);
    }
    public List<City> getAll(){
        return cityRepository.findAll();
    }
    public boolean deleteById(long id){
        try {
            cityRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public City updateById(long id , City city){
        City city1= cityRepository.findById(id).get();
        city1.setNome(city.getNome());
        return cityRepository.save(city);
    }

    public  List<City> getByNationId(long nationId){
        return cityRepository.findByNation_Id(nationId);
    }
    public City findById(long id){
        return cityRepository.findById(id).get();
    }
}
