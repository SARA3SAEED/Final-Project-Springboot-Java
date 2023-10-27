package com.PointOfSales.POS.Controller;


import com.PointOfSales.POS.DTO.AddProductReqDTO;
import com.PointOfSales.POS.Entity.Product;
import com.PointOfSales.POS.Service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/Allproduct")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> add(@RequestBody AddProductReqDTO dto) {
        Product products = productService.addProduct(dto);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
