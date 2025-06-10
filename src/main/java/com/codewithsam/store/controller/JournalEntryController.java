package com.codewithsam.store.controller;

import com.codewithsam.store.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntries=new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<JournalEntry>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
        return true;
    }
    @GetMapping("id/{myID}")
    public JournalEntry getById(@PathVariable long myID) {
        return journalEntries.get(myID);
    }
    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable long id, @RequestBody JournalEntry myEntry) {
        return journalEntries.put(id, myEntry);

    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteEntry(@PathVariable long myId) {
        return journalEntries.remove(myId);
    }
}
