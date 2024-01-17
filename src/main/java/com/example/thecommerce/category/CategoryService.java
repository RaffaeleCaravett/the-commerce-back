package com.example.thecommerce.category;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
@Autowired
    Cloudinary cloudinary;
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

    public String uploadAvatar(long id, MultipartFile file) throws IOException {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

            Category utente = categoryRepository.findById(id).orElse(null);
            if (utente != null) {
                utente.setImage(imageUrl);
                categoryRepository.save(utente);
            }

            return imageUrl;
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare l'immagine", e);
        }
    }
}
