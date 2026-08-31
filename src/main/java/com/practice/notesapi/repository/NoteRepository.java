package com.practice.notesapi.repository;

import com.practice.notesapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
