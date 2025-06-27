import React, { useState } from 'react';

const AddJournal = ({ addJournal }) => {
  const [id, setId] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');

  const submit = (e) => {
    e.preventDefault();
    if (!id || !title || !content) {
      alert('All fields are required!');
      return;
    }
    addJournal({ id: parseInt(id), title, content });
    setId('');
    setTitle('');
    setContent('');
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
      <button type="submit" className="btn btn-success">
        Done
      </button>
    </form>
  );
};

export default AddJournal;
