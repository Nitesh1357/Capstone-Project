package com.project.Capstone.ecommerce.service;

import com.project.Capstone.common.exception.ResourceNotFoundException;
import com.project.Capstone.ecommerce.dto.request.ProductRequestDto;
import com.project.Capstone.ecommerce.dto.response.ProductResponseDto;
import com.project.Capstone.ecommerce.mapper.ProductMapper;
import com.project.Capstone.ecommerce.model.Category;
import com.project.Capstone.ecommerce.model.Product;
import com.project.Capstone.ecommerce.repository.CategoryRepository;
import com.project.Capstone.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Create a new product under a specified category
     *
     * @param dto ProductRequestDto containing product data
     * @return ProductResponseDto after saving
     */
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        log.info("Creating product with name: {} under category ID: {}", dto.getName(), dto.getCategoryId());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", dto.getCategoryId()));

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .build();

        Product savedProduct = productRepository.save(product);
        log.info("Product created successfully with ID: {}", savedProduct.getId());

        return ProductMapper.toDto(savedProduct);
    }

    /**
     * Fetch all products as list of response DTOs
     *
     * @return List<ProductResponseDto>
     */
    public List<ProductResponseDto> getAllProducts() {
        log.info("Fetching all products");

        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productDtos = products.stream()
                .map(ProductMapper::toDto)
                .toList();

        log.info("Total products found: {}", productDtos.size());
        return productDtos;
    }

    /**
     * Get single product by ID
     *
     * @param productId Long
     * @return ProductResponseDto
     */
    public ProductResponseDto getProductById(Long productId) {
        log.info("Fetching product with ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        return ProductMapper.toDto(product);
    }

    /**
     * Delete product by ID
     *
     * @param productId Long
     */
    public void deleteProduct(Long productId) {
        log.info("Deleting product with ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);
        log.info("Product deleted successfully with ID: {}", productId);
    }

    /**
     * Update product by ID
     *
     * @param productId Long
     * @param dto       ProductRequestDto with updated info
     * @return ProductResponseDto
     */
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto dto) {
        log.info("Updating product with ID: {}", productId);

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", dto.getCategoryId()));

        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setImageUrl(dto.getImageUrl());
        existingProduct.setCategory(category);

        Product updated = productRepository.save(existingProduct);
        log.info("Product updated successfully with ID: {}", updated.getId());

        return ProductMapper.toDto(updated);
    }
}
