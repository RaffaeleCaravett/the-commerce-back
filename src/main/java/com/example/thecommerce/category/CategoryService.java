package com.example.thecommerce.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }
    public Category updateById(long id,Category category){
        Category category1=categoryRepository.findById(id).get();

        category1.setName(category.getName());
return categoryRepository.save(category);
    }

    public boolean deleteById(long id){
        try {
            categoryRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
