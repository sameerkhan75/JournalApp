package com.codewithsam.store.dto;

import com.codewithsam.store.entity.Image;
import java.time.LocalDateTime;

public class ImageResponseDto {
    private Long id;
    private String fileName;
    private String originalFileName;
    private String fileType;
    private Long fileSize;
    private String downloadUrl;
    private LocalDateTime uploadedAt;
    private String description;
    
    public ImageResponseDto() {}
    
    public ImageResponseDto(Image image, String baseUrl) {
        this.id = image.getId();
        this.fileName = image.getFileName();
        this.originalFileName = image.getOriginalFileName();
        this.fileType = image.getFileType();
        this.fileSize = image.getFileSize();
        this.downloadUrl = baseUrl + "/api/images/download/" + image.getFileName();
        this.uploadedAt = image.getUploadedAt();
        this.description = image.getDescription();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getOriginalFileName() {
        return originalFileName;
    }
    
    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
    
    public String getFileType() {
        return fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public Long getFileSize() {
        return fileSize;
    }
    
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getDownloadUrl() {
        return downloadUrl;
    }
    
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
    
    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
} 