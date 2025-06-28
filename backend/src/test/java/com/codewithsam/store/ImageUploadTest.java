package com.codewithsam.store;

import com.codewithsam.store.entity.Image;
import com.codewithsam.store.service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "app.file.upload-dir=./test-uploads"
})
public class ImageUploadTest {
    
    @Autowired
    private ImageService imageService;
    
    @Test
    public void testImageUpload() {
        // Create a mock image file
        MockMultipartFile mockFile = new MockMultipartFile(
            "file",
            "test-image.jpg",
            "image/jpeg",
            "fake image content".getBytes()
        );
        
        // Test upload
        Image uploadedImage = imageService.uploadImage(mockFile, "Test image description");
        
        // Assertions
        assertNotNull(uploadedImage);
        assertNotNull(uploadedImage.getId());
        assertEquals("test-image.jpg", uploadedImage.getOriginalFileName());
        assertEquals("image/jpeg", uploadedImage.getFileType());
        assertEquals("Test image description", uploadedImage.getDescription());
        assertTrue(uploadedImage.getFileName().endsWith(".jpg"));
    }
} 