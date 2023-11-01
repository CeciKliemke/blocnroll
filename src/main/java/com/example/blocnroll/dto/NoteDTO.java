package com.example.blocnroll.dto;

import com.example.blocnroll.entities.Note;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class NoteDTO {
    
@NotBlank(message = "A anotação é obrigatória")
    private String notepad;

    public NoteDTO(Note note) {
        notepad = note.getNotepad();
    }

    public Note toNote() {
        return new Note(null, notepad, null);
    }

}
