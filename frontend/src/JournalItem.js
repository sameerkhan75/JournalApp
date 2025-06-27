import React from 'react';

const getRandomImage = () => {
  const images = [
    'https://source.unsplash.com/400x200/?journal,writing',
    'https://source.unsplash.com/400x200/?notebook,thoughts',
    'https://source.unsplash.com/400x200/?diary,paper',
    'https://source.unsplash.com/400x200/?memories,story',
  ];
  return images[Math.floor(Math.random() * images.length)];
};

const JournalItem = ({ journal, deleteJournal }) => {
  return (
    <div className="card my-3">
      <img src={getRandomImage()} className="card-img-top" alt="Journal" />
      <div className="card-body">
        <h5 className="card-title">{journal.title}</h5>
        <p className="card-text">{journal.content}</p>
        <button
          className="btn btn-danger"
          onClick={() => deleteJournal(journal.id)}
        >
          Delete
        </button>
      </div>
    </div>
  );
};

export default JournalItem;
