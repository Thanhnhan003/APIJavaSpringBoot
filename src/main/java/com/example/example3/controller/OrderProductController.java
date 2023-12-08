package com.example.example3.controller;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.example3.entity.OrderProduct;
import com.example.example3.service.OrderProductService;

@RestController
@AllArgsConstructor
@RequestMapping("api/orderproducts")
@CrossOrigin(origins = "*", exposedHeaders = "Content-Range")
public class OrderProductController {

    private OrderProductService orderProductService;

    // Create OrderProduct REST API
    @PostMapping
    public ResponseEntity<OrderProduct> createOrderProduct(@RequestBody OrderProduct orderProduct) {
        OrderProduct savedOrderProduct = orderProductService.createOrderProduct(orderProduct);
        return new ResponseEntity<>(savedOrderProduct, HttpStatus.CREATED);
    }

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam("customName") String customName) {
        try {
            // Save the uploaded image to a database or a file system
            String uploadDir = "src/main/resources/static/dataImage"; // Set your desired directory path

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generate a unique filename for the uploaded image (you may need to modify
            // this logic)
            // String fileName = file.getOriginalFilename();

            String filePath = uploadDir + File.separator + customName + ".png";

            // Save the uploaded image to the specified directory
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(file.getBytes());
            }
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        // You should sanitize and validate the imageName parameter to prevent directory
        // traversal attacks.
        // In this example, it is assumed that imageName is just the name of the image
        // file.

        Resource resource = new ClassPathResource("static/dataImage/" + imageName);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate image media type

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    // Get OrderProduct by id REST API
    @GetMapping("{id}")
    public ResponseEntity<OrderProduct> getOrderProductById(@PathVariable("id") Long orderProductId) {
        OrderProduct orderProduct = orderProductService.getOrderProductById(orderProductId);
        return new ResponseEntity<>(orderProduct, HttpStatus.OK);
    }

    // Get All OrderProducts REST API
    @GetMapping
    public ResponseEntity<Page<OrderProduct>> getAllOrderProducts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderProduct> orderProductPage = orderProductService.getAllOrderProduct(pageable);

        return new ResponseEntity<>(orderProductPage, HttpStatus.OK);
    }

    // Update OrderProduct REST API
    @PutMapping("{id}")
    public ResponseEntity<OrderProduct> updateOrderProduct(@PathVariable("id") Long orderProductId,
            @RequestBody OrderProduct orderProduct) {
        orderProduct.setId(orderProductId);
        OrderProduct updatedOrderProduct = orderProductService.updateOrderProduct(orderProduct);
        return new ResponseEntity<>(updatedOrderProduct, HttpStatus.OK);
    }

    // Delete OrderProduct REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderProduct(@PathVariable("id") Long orderProductId) {
        orderProductService.deleteOrderProduct(orderProductId);
        return new ResponseEntity<>("OrderProduct successfully deleted!", HttpStatus.OK);
    }
}
