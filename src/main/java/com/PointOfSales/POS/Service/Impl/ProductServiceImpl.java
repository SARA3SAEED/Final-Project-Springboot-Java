package com.PointOfSales.POS.Service.Impl;


import com.PointOfSales.POS.DTO.AddProductReqDTO;
import com.PointOfSales.POS.Entity.DigitalProduct;
import com.PointOfSales.POS.Entity.PhysicalProduct;
import com.PointOfSales.POS.Entity.Product;
import com.PointOfSales.POS.Repository.DigitalProductRepo;
import com.PointOfSales.POS.Repository.PhysicalProductRepo;
import com.PointOfSales.POS.Repository.ProductRepo;
import com.PointOfSales.POS.enums.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {
    private final ProductRepo productRepo;
    private final DigitalProductRepo digitalProductRepo;
    private final PhysicalProductRepo physicalProductRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getByBarcode(Integer barcode) {
        return this.productRepo.findByBarcod(barcode).orElse(null);
    }

    public Product addProduct(AddProductReqDTO dto) {
        Product product = null;

        if (dto.getType().equals(ProductType.PHYSICAL)) {
            product = new PhysicalProduct(dto.getWeight(), dto.getHeight());
        } else if (dto.getType().equals(ProductType.DIGITAL)) {
            product = new DigitalProduct(dto.getSerialNo(), dto.getProvider());
        }

        // shared data
        product.setProduct_name(dto.getProduct_name());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setBarcod(dto.getBarcod());

        Product savedProduct = null;



        if (dto.getType().equals(ProductType.PHYSICAL)) {
            savedProduct = this.physicalProductRepo.save(product);
        } else if (dto.getType().equals(ProductType.DIGITAL)) {
            savedProduct = this.digitalProductRepo.save(product);
        }

        return savedProduct;
    }

    public String deleteProduct(Integer barcode) {
        try {
            Optional<Product> productOptional = productRepo.findByBarcod(barcode);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                productRepo.delete(product);
                return "Product deleted successfully";
            } else {
                return "Product not found";
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., database errors) as needed
            return "Error deleting product";
        }
    }
}
