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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<String, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<JournalEntry>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry) {
        journalEntries.put(myEntry.getDate(), myEntry);
        return true;
    }
    @GetMapping("date/{date}")
    public JournalEntry getByDate(@PathVariable String date) {
        return journalEntries.get(date);
    }
    @PutMapping("date/{date}")
    public JournalEntry updateEntry(@PathVariable String date, @RequestBody JournalEntry myEntry) {
        return journalEntries.put(date, myEntry);
    }
    @DeleteMapping("date/{date}")
    public JournalEntry deleteEntry(@PathVariable String date) {
        return journalEntries.remove(date);
    }
    //helps to get login
}
