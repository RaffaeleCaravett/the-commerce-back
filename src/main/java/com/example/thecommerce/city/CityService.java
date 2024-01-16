package com.example.thecommerce.city;

import com.example.thecommerce.category.Category;
import com.example.thecommerce.nation.NationRepository;
import com.example.thecommerce.payloads.entities.CityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    NationRepository nationRepository;

    public City save(CityDTO city){
        City city1=new City();
        city1.setNome(city.nome());
        city1.setNation(nationRepository.findById(city.nation_id()).get());


        return cityRepository.save(city1);
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

    public City updateById(long id , CityDTO city){
        City city1= cityRepository.findById(id).get();
        city1.setNome(city.nome());
        city1.setNation(nationRepository.findById(city.nation_id()).get());


        return cityRepository.save(city1);
    }

    public  List<City> getByNationId(long nationId){
        return cityRepository.findByNation_Id(nationId);
    }
    public City findById(long id){
        return cityRepository.findById(id).get();
    }
}
