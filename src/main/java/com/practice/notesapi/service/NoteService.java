package com.practice.notesapi.service;

import com.practice.notesapi.model.Note;
import com.practice.notesapi.repository.NoteRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    // Cached in Redis under key "notes::<id>" so repeated GETs skip Postgres
    @Cacheable(value = "notes", key = "#id")
    public Note findById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note " + id + " not found"));
    }

    public Note create(Note note) {
        return noteRepository.save(note);
    }

    @CacheEvict(value = "notes", key = "#id")
    public Note update(Long id, Note payload) {
        Note existing = findByIdUncached(id);
        existing.setTitle(payload.getTitle());
        existing.setContent(payload.getContent());
        return noteRepository.save(existing);
    }

    @CacheEvict(value = "notes", key = "#id")
    public void delete(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new NoSuchElementException("Note " + id + " not found");
        }
        noteRepository.deleteById(id);
    }

    private Note findByIdUncached(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note " + id + " not found"));
    }
}
