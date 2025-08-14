package com.project.ecommerce.controller;

import com.project.ecommerce.dto.ProductDetailsDTO;
import com.project.ecommerce.dto.ProductSummaryDTO;
import com.project.ecommerce.entity.Product;
import com.project.ecommerce.exception.MissingFilterParameterException;
import com.project.ecommerce.exception.MissingSearchParameterException;
import com.project.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
        System.out.println("hii");
    }


    @GetMapping("/products")
    public Page<ProductSummaryDTO> getAllProducts(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestHeader("X-User-Id") Long userId) {

        System.out.println("Request by user: " + userId);
        return productService.getAllProductSummaries(pageable);
    }

    @GetMapping("/products/filter")
    public ResponseEntity<List<ProductDetailsDTO>> getAllProductDetailsSorted(
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String order,
            @RequestHeader("X-User-Id") Long userId) {

        if (sortBy == null) {
            throw new MissingFilterParameterException("Missing required parameter: SortBy");
        }

        System.out.println("Request by user: " + userId);
        List<ProductDetailsDTO> products = productService.getAllProductDetailsSorted(sortBy, order);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDetailsDTO> getProductDetailsWithReviews(
            @PathVariable Long productId,
            @RequestHeader("X-User-Id") Long userId) {

        System.out.println("Request by user: " + userId);
        ProductDetailsDTO productDetails = productService.getProductDetailsWithReviews(productId);
        return ResponseEntity.ok(productDetails);
    }

    @GetMapping("/products/search")
    public Page<ProductDetailsDTO> searchProducts(
            @RequestParam(name = "query", required = false) String query,
            @PageableDefault(size = 10) Pageable pageable,
            @RequestHeader("X-User-Id") Long userId) {

        if (query == null) {
            throw new MissingSearchParameterException("Missing required parameter: query");
        }

        System.out.println("Request by user: " + userId);
        return productService.searchProducts(query, pageable);
    }
}