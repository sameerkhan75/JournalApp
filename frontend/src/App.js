import React, { useState, useEffect } from 'react';
import AddJournal from './AddJournal';
import JournalList from './JournalList';
import axios from 'axios';

function App() {
  const [journals, setJournals] = useState([]);

  const fetchJournals = async () => {
    const res = await axios.get('http://localhost:8080/journal');
    setJournals(res.data);
  };

  useEffect(() => {
    fetchJournals();
  }, []);

  const addJournal = async (journal) => {
    await axios.post('http://localhost:8080/journal', journal);
    fetchJournals();
  };

  const deleteJournal = async (id) => {
    await axios.delete(`http://localhost:8080/journal/id/${id}`);
    fetchJournals();
  };

  return (
    <div className="container my-4">
      <h1 className="text-center">LivingTheMoment</h1>
      <AddJournal addJournal={addJournal} />
      <JournalList journals={journals} deleteJournal={deleteJournal} />
    </div>
  );
}

export default App;
