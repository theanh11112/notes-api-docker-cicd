package com.practice.notesapi.service;

import com.practice.notesapi.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @Test
    void countNotes_shouldReturnRepositoryCount() {
        // Giả lập: khi repository.count() được gọi, trả về 5
        when(noteRepository.count()).thenReturn(5L);

        // NoteService không có @Mock nên bạn tự tạo, truyền mock vào qua constructor
        NoteService noteService = new NoteService(noteRepository);

        long result = noteService.countNotes();

        assertEquals(5L, result);
    }
}
