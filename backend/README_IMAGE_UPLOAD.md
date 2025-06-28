# Image Upload Functionality

This Spring Boot application now supports image upload functionality with the following features:

## Features

- Upload images (JPG, JPEG, PNG, GIF, BMP, WebP)
- File size validation (max 10MB)
- File type validation
- Unique filename generation
- Image metadata storage in database
- Download images via REST API
- Update image descriptions
- Delete images
- List all uploaded images
- CORS support for frontend integration

## API Endpoints

### 1. Upload Image
```
POST /api/images/upload
Content-Type: multipart/form-data

Parameters:
- file: The image file to upload
- description: Optional description for the image
```

### 2. Download Image
```
GET /api/images/download/{fileName}
```

### 3. Get All Images
```
GET /api/images
```

### 4. Get Image by ID
```
GET /api/images/{id}
```

### 5. Update Image Description
```
PUT /api/images/{id}/description
Content-Type: application/json

Body:
{
  "description": "Updated description"
}
```

### 6. Delete Image
```
DELETE /api/images/{id}
```

## Frontend Integration Example

```javascript
// Upload image
const formData = new FormData();
formData.append('file', imageFile);
formData.append('description', 'My uploaded image');

fetch('http://localhost:8080/api/images/upload', {
  method: 'POST',
  body: formData
})
.then(response => response.json())
.then(data => {
  if (data.success) {
    console.log('Image uploaded:', data.image);
    // Display image: http://localhost:8080/api/images/download/{data.image.fileName}
  }
});
```

## Configuration

Images are stored in `./uploads/images` directory and metadata in H2 database.
File size limit: 10MB
Supported formats: JPG, JPEG, PNG, GIF, BMP, WebP 