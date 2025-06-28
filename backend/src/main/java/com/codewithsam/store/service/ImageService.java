package com.codewithsam.store.service;

import com.codewithsam.store.entity.Image;
import com.codewithsam.store.repository.ImageRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {
    
    private final ImageRepository imageRepository;
    private final Path fileStorageLocation;
    
    @Value("${app.file.upload-dir}")
    private String uploadDir;
    
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    
    public Image uploadImage(MultipartFile file, String description) {
        // Validate file
        validateFile(file);
        
        // Generate unique filename
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = FilenameUtils.getExtension(originalFileName);
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
        
        try {
            // Check if the filename contains invalid characters
            if (originalFileName.contains("..")) {
                throw new RuntimeException("Filename contains invalid path sequence " + originalFileName);
            }
            
            // Copy file to the target location
            Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            // Create and save image entity
            Image image = new Image();
            image.setFileName(uniqueFileName);
            image.setOriginalFileName(originalFileName);
            image.setFileType(file.getContentType());
            image.setFileSize(file.getSize());
            image.setFilePath(targetLocation.toString());
            image.setDescription(description);
            
            return imageRepository.save(image);
            
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + originalFileName, ex);
        }
    }
    
    public Resource loadImageAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }
    
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
    
    public Optional<Image> getImageById(Long id) {
        return imageRepository.findById(id);
    }
    
    public Optional<Image> getImageByFileName(String fileName) {
        return imageRepository.findByFileName(fileName);
    }
    
    public Image updateImageDescription(Long id, String description) {
        Optional<Image> imageOpt = imageRepository.findById(id);
        if (imageOpt.isPresent()) {
            Image image = imageOpt.get();
            image.setDescription(description);
            return imageRepository.save(image);
        }
        throw new RuntimeException("Image not found with id: " + id);
    }
    
    public void deleteImage(Long id) {
        Optional<Image> imageOpt = imageRepository.findById(id);
        if (imageOpt.isPresent()) {
            Image image = imageOpt.get();
            
            // Delete file from filesystem
            try {
                Path filePath = Paths.get(image.getFilePath());
                Files.deleteIfExists(filePath);
            } catch (IOException ex) {
                throw new RuntimeException("Could not delete file: " + image.getFileName(), ex);
            }
            
            // Delete from database
            imageRepository.deleteById(id);
        } else {
            throw new RuntimeException("Image not found with id: " + id);
        }
    }
    
    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file");
        }
        
        // Check file size (10MB limit)
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new RuntimeException("File size exceeds 10MB limit");
        }
        
        // Check file type
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("Only image files are allowed");
        }
        
        // Check file extension
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null) {
            String extension = FilenameUtils.getExtension(originalFilename).toLowerCase();
            if (!isValidImageExtension(extension)) {
                throw new RuntimeException("Invalid image file extension: " + extension);
            }
        }
    }
    
    private boolean isValidImageExtension(String extension) {
        return extension != null && (
            extension.equals("jpg") || 
            extension.equals("jpeg") || 
            extension.equals("png") || 
            extension.equals("gif") || 
            extension.equals("bmp") || 
            extension.equals("webp")
        );
    }
} 