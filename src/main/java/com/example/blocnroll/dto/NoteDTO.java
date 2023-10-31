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

    private int year;
    private String color;

    public NoteDTO(note note) {
        notepad = note.getNotepad();
        year = note.getYear();
    }

    public note tonote() {
        return new Note(null, notepad, year, color, null);
    }

}
