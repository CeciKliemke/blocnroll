package com.example.blocnroll.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blocnroll.entities.Note;

public interface NoteRepo extends JpaRepository<Note,Long> {
   Optional<Note> findByNotepad(String notepad);
}