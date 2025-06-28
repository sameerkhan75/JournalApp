import React from 'react';

const JournalItem = ({ journal, deleteJournal }) => {
  const defaultImage = './work.jpeg';
  
  return (
    <div className="card my-3">
      <img 
        src={journal.image || defaultImage} 
        className="card-img-top" 
        alt="Journal" 
        style={{ height: '200px', objectFit: 'cover' }}
      />
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
