package com.example.bt4.service;

import com.example.bt4.model.Product;
import com.example.bt4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public void add(Product newProduct) {
        productRepository.save(newProduct);
    }

    public Product get(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void update(Product editProduct) {
        productRepository.save(editProduct);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> search(String keyword, Integer categoryId) {
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasCategoryId = categoryId != null && categoryId != 0;

        if (hasKeyword && hasCategoryId) {
            return productRepository.findByNameContainingIgnoreCaseAndCategoryId(keyword, categoryId);
        } else if (hasKeyword) {
            return productRepository.findByNameContainingIgnoreCase(keyword);
        } else if (hasCategoryId) {
            return productRepository.findByCategoryId(categoryId);
        }
        return productRepository.findAll();
    }

    public void updateImage(Product newProduct, MultipartFile imageProduct) {
        String contentType = imageProduct.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return;
        }
        if (!imageProduct.isEmpty()) {
            try {
                Path dirImages = Paths.get("static/images");
                if (!Files.exists(dirImages)) {
                    Files.createDirectories(dirImages);
                }
                String newFilename = UUID.randomUUID() + "_" + imageProduct.getOriginalFilename();
                Path pathFileUpload = dirImages.resolve(newFilename);
                Files.copy(imageProduct.getInputStream(), pathFileUpload, StandardCopyOption.REPLACE_EXISTING);
                newProduct.setImage(newFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
