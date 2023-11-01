package com.example.blocnroll.service;

import java.util.Optional;

import com.example.blocnroll.exceptions.OwnerNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blocnroll.dto.NoteDTO;
import com.example.blocnroll.entities.Note;
import com.example.blocnroll.entities.Owner;
import com.example.blocnroll.repository.NoteRepo;
import com.example.blocnroll.repository.OwnerRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class NoteService {
    private final NoteRepo noteRepo;
    private final OwnerRepo ownerRepo;

    public Note saveNote(Note newNote, long idProprietatio) {
        Owner owner = ownerRepo.findById(idProprietatio)
            .orElseThrow(()-> new OwnerNotFoundException("Proprietário da note não existe."));
        
        newNote.setOwner(owner);
        return NoteRepo.save(newNote);
    }

    public Optional<Note> findById(long id) {
        return noteRepo.findById(id);
    }

    public Optional<Note> findByNotepad(String notepad) {
        Optional<Note> noteOptional = noteRepo.findByNotepad(notepad);

        if(noteOptional.isPresent()){
            Note noteFound = noteOptional.get();
            return Optional.of(noteFound);
        }
        return Optional.empty();
    }

    public Optional<NoteDTO> update(NoteDTO noteDTO) {
        Optional<Note> notefound = noteRepo.findByNotepad(NoteDTO.getNotepad());
        if(notefound.isEmpty()) {
            return Optional.empty();
        }
        Note note = notefound.get();

        Note noteUpdated = noteRepo.save(note);

        return Optional.of(new NoteDTO(noteUpdated));
    }

    public boolean delete(String notepad) {
        Optional<note> notefound = noteRepo.findByNotepad(notepad);
        if(notefound.isEmpty()) {
            return false;
        }

        noteRepo.deleteById(notefound.get().getId());
        return true;
    }
    
    }
}
