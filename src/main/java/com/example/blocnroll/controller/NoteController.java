package com.example.blocnroll.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blocnroll.dto.NoteDTO;
import com.example.blocnroll.entities.Note;
import com.example.blocnroll.service.NoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/note")

public class NoteController {
    
    @Autowired
    private NoteService NoteService;

    @PostMapping("/{id}")
    public ResponseEntity<Note> create(@Valid @RequestBody NoteDTO NotedDto, @PathVariable long id) {
        Note NoteSaved = NoteService.saveNote(NotedDto.toNote(), id);
        return ResponseEntity.status(HttpStatus.CREATED).body(NoteSaved);
    }

    @GetMapping("/{notepad}")
    public ResponseEntity<Note> findByNotepad(@PathVariable String notepad) {
        Optional<Note> Note =  NoteService.findByNotepad(notepad);

        if(Note.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Note.get());
    }

    @PatchMapping
    public ResponseEntity<NoteDTO> update(@RequestBody NoteDTO NoteDTO) {
        Optional<NoteDTO> NoteOptional = NoteService.update(NoteDTO);
        if(NoteOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(NoteOptional.get());
    }

    @DeleteMapping("/{notepad}")
    public ResponseEntity<Void> delete(@PathVariable String notepad) {
        boolean response = NoteService.delete(notepad);
        if(response) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
