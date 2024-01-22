package com.example.thecommerce.product;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.thecommerce.category.Category;
import com.example.thecommerce.category.CategoryRepository;
import com.example.thecommerce.enums.TipoProdotto;
import com.example.thecommerce.payloads.entities.ProductDTO;
import com.example.thecommerce.società.SocietàRepository;
import org.apache.coyote.BadRequestException;
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
        if(productRepository.findBySocieta_Id(product.societa_id()).size()==10){
            throw new BadRequestException("Limite prodotti in vendita raggiunto. Elimina qualche prodotto per continuare a venderne di nuovi");
        }

        List<Product> products = productRepository.findBySocieta_Id(product.societa_id());
        if(!products.isEmpty()){
            for(Product p : products){
                if(p.getNome().equals(product.nome())){
                    throw new BadRequestException("Esiste già un tuo prodotto con questo nome");
                }
            };
        }
        Product product1= new Product();

        product1.setNome(product.nome());
        product1.setTipoProdotto(TipoProdotto.valueOf(product.tipoProdotto()));
        product1.setCategory(categoryRepository.findById(product.category_id()).get());
        product1.setPezzi(product.pezzi());
        product1.setPrezzo(product.prezzo());
        product1.setSocieta(societàRepository.findById(product.societa_id()).get());
        product1.setSocietàName(product1.getSocieta().getNome());
        System.out.println(product1.getNome());
        System.out.println(product1.getSocieta());
        System.out.println(product1.getSocieta().getNome());
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
        if(!product.getNome().equals(productDTO.nome())){
            List<Product> products = productRepository.findBySocieta_Id(productDTO.societa_id());
            if(!products.isEmpty()){
                for(Product p : products){
                    if(p.getNome().equals(productDTO.nome())){
                        throw new BadRequestException("Esiste già un tuo prodotto con questo nome");
                    }
                };
            }
        }

        product.setNome(productDTO.nome());
        product.setCategory(categoryRepository.findById(productDTO.category_id()).get());
        product.setPezzi(productDTO.pezzi());
        product.setPrezzo(productDTO.prezzo());
        product.setTipoProdotto(TipoProdotto.valueOf(productDTO.tipoProdotto()));
        if(multipartFile != null && !multipartFile.isEmpty()){
            uploadProductImage(multipartFile,product);
        }
        return productRepository.save(product);
    }

    public Page<Product> findByCategoryId(long categoryId,int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return productRepository.findByCategory_Id(categoryId,pageable);
    }
    public List<Product> findBySocietàId(long societàId){
        return productRepository.findBySocieta_Id(societàId);
    }
    public Page<Product> getAll(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return productRepository.findAll(pageable);
    }

}
