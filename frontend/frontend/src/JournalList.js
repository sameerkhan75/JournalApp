import React from 'react';
import JournalItem from './JournalItem';

const JournalList = ({ journals, deleteJournal }) => {
  return (
    <div className="mt-4">
      <h3>Your Journal Entries</h3>
      {journals.length === 0 ? (
        <p>No journal entries available.</p>
      ) : (
        journals.map((journal) => (
          <JournalItem key={journal.id} journal={journal} deleteJournal={deleteJournal} />
        ))
      )}
    </div>
  );
};

export default JournalList;
