import React, { useState } from 'react';

const AddJournal = ({ addJournal }) => {
  const [id, setId] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [image, setImage] = useState(null);
  const [imagePreview, setImagePreview] = useState('');

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setImage(file);
      const reader = new FileReader();
      reader.onloadend = () => {
        setImagePreview(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  const submit = (e) => {
    e.preventDefault();
    if (!id || !title || !content) {
      alert('All fields are required!');
      return;
    }
    
    const journalData = { id: parseInt(id), title, content, image: imagePreview };
    addJournal(journalData);
    setId('');
    setTitle('');
    setContent('');
    setImage(null);
    setImagePreview('');
  };

  return (
    <form onSubmit={submit}>
      <h3>Add Journal Entry</h3>
      <div className="mb-2">
        <input
          type="number"
          className="form-control"
          placeholder="ID"
          value={id}
          onChange={(e) => setId(e.target.value)}
        />
      </div>
      <div className="mb-2">
        <input
          type="text"
          className="form-control"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
      </div>
      <div className="mb-2">
        <textarea
          className="form-control"
          placeholder="Content"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        ></textarea>
      </div>
      <div className="mb-2">
        <label htmlFor="image-upload" className="form-label">share the glimps of your day</label>
        <input
          type="file"
          className="form-control"
          id="image-upload"
          accept="image/*"
          onChange={handleImageChange}
        />
      </div>
      {imagePreview && (
        <div className="mb-2">
          <img 
            src={imagePreview} 
            alt="Preview" 
            className="img-thumbnail" 
            style={{ maxWidth: '200px', maxHeight: '200px' }}
          />
        </div>
      )}
      <button type="submit" className="btn btn-success">
        Done
      </button>
    </form>
  );
};

export default AddJournal;
