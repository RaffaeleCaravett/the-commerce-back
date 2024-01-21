package com.example.thecommerce.product;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.thecommerce.category.Category;
import com.example.thecommerce.category.CategoryRepository;
import com.example.thecommerce.enums.TipoProdotto;
import com.example.thecommerce.payloads.entities.ProductDTO;
import com.example.thecommerce.società.SocietàRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
@Autowired
    CategoryRepository categoryRepository;
@Autowired
    SocietàRepository societàRepository;
    @Autowired
    private Cloudinary cloudinary;
    public Product save(ProductDTO product,MultipartFile multipartFile) throws IOException {
        Product product1= new Product();

        product1.setNome(product.nome());
        product1.setTipoProdotto(TipoProdotto.valueOf(product.tipoProdotto()));
        List<Category> categories =new ArrayList<>();
        for(Long l : product.category_id()){
            categories.add(categoryRepository.findById(l).get());
        }        product1.setPezzi(product.pezzi());
        product1.setPrezzo(product.prezzo());
        product1.setSocieta(societàRepository.findById(product.societa_id()).get());
uploadProductImage(multipartFile,product1);
return product1;
    }
    public String uploadProductImage(MultipartFile file,Product product) throws IOException {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

            if (product != null) {
                product.setImmagine(imageUrl);
                productRepository.save(product);
            }

            return imageUrl;
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare l'immagine", e);
        }
    }

    public boolean deleteById(long id){
        try{
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Product updateById(long id, ProductDTO productDTO,MultipartFile multipartFile) throws IOException {
        Product product= productRepository.findById(id).get();
        product.setNome(productDTO.nome());

        List<Category> categories =new ArrayList<>();
        for(Long l : productDTO.category_id()){
            categories.add(categoryRepository.findById(l).get());
        }
        product.setCategory(categories);
        product.setPezzi(productDTO.pezzi());
        product.setPrezzo(productDTO.prezzo());
        product.setTipoProdotto(TipoProdotto.valueOf(productDTO.tipoProdotto()));
        uploadProductImage(multipartFile,product);
        return productRepository.save(product);
    }

    public Page<Product> findByCategoryId(long categoryId,int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return productRepository.findByCategory_Id(categoryId,pageable);
    }
    public Page<Product> findBySocietàId(long societàId,int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return productRepository.findBySocieta_Id(societàId,pageable);
    }
    public Page<Product> getAll(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return productRepository.findAll(pageable);
    }

}
