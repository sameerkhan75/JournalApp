import React, { useState } from 'react';

const AddJournal = ({ addJournal }) => {
  const [date, setDate] = useState('');
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
    if (!date || !title || !content) {
      alert('All fields are required!');
      return;
    }
    
    const journalData = { date, title, content, image: imagePreview };
    addJournal(journalData);
    setDate('');
    setTitle('');
    setContent('');
    setImage(null);
    setImagePreview('');
  };

  return (
    <form onSubmit={submit}>
      <h3>Share your day</h3>
      <div className="mb-2">
        <label htmlFor="date-input" className="form-label">Day?</label>
        <input
          type="date"
          className="form-control"
          placeholder="Date"
          value={date}
          onChange={(e) => setDate(e.target.value)}
        />
      </div>
      <div className="mb-2">
        <input
          type="text"
          className="form-control"
          placeholder="Highlights"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
      </div>
      <div className="mb-2">
        <textarea
          className="form-control"
          placeholder="Narrate your day"
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
