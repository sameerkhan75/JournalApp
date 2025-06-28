package com.codewithsam.store.repository;

import com.codewithsam.store.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    Optional<Image> findByFileName(String fileName);
    
    List<Image> findByFileType(String fileType);
    
    List<Image> findByOriginalFileNameContainingIgnoreCase(String originalFileName);
    
    List<Image> findByUploadedAtBetween(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
} 