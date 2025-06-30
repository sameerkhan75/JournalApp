import React from 'react';
import JournalItem from './JournalItem';

const JournalList = ({ journals, deleteJournal }) => {
  return (
    <div className="mt-4">
      <h3>Your Journal Entries</h3>
      {journals.length === 0 ? (
        <p>No journal entries available.</p>
      ) : (
        <div className="row">
          {journals.map((journal, idx) => (
            <div className="col-md-4 d-flex" key={idx}>
              <JournalItem journal={journal} deleteJournal={deleteJournal} />
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default JournalList;
