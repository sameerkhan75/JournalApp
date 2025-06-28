package com.codewithsam.store.controller;

import com.codewithsam.store.entity.Image;
import com.codewithsam.store.service.ImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/images")
public class ImageController {
    
    private final ImageService imageService;
    
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            Image uploadedImage = imageService.uploadImage(file, description);
            
            response.put("success", true);
            response.put("message", "Image uploaded successfully");
            response.put("image", uploadedImage);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to upload image: " + e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileName) {
        try {
            Resource resource = imageService.loadImageAsResource(fileName);
            
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // You might want to determine this dynamically
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .body(resource);
                    
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.getAllImages();
        return ResponseEntity.ok(images);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        Optional<Image> image = imageService.getImageById(id);
        
        if (image.isPresent()) {
            return ResponseEntity.ok(image.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}/description")
    public ResponseEntity<Map<String, Object>> updateImageDescription(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        
        Map<String, Object> response = new HashMap<>();
        String description = request.get("description");
        
        try {
            Image updatedImage = imageService.updateImageDescription(id, description);
            
            response.put("success", true);
            response.put("message", "Image description updated successfully");
            response.put("image", updatedImage);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update image description: " + e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteImage(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            imageService.deleteImage(id);
            
            response.put("success", true);
            response.put("message", "Image deleted successfully");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to delete image: " + e.getMessage());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
    @GetMapping("/default")
    public ResponseEntity<Map<String, Object>> getDefaultImage() {
        Map<String, Object> response = new HashMap<>();
        
        // You can return a default image URL or base64 encoded image
        response.put("defaultImageUrl", "/api/images/default-placeholder");
        response.put("message", "Default image placeholder");
        
        return ResponseEntity.ok(response);
    }
    
    // Exception handler for better error responses
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", "An error occurred: " + e.getMessage());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
} 