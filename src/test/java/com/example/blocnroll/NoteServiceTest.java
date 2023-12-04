package com.example.blocnroll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.example.blocnroll.service.NoteService;
import com.example.blocnroll.entities.Note;
import com.example.blocnroll.entities.Owner;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Test
    public void testSaveNote() {
        Owner owner = new Owner();
        Note newNote = new Note();

        Note savedNote = noteService.saveNote(newNote, owner.getId());

        assertNotNull(savedNote);
        assertEquals(owner, savedNote.getOwner());
    }


}
