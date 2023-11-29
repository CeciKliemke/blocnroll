package com.example.blocnroll.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.blocnroll.entities.Owner;
import com.example.blocnroll.entities.Note;
import com.example.blocnroll.service.OwnerService;

@RestController
@RequestMapping("/owner")

public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Owner> create(@RequestBody Owner owner) {
        return ResponseEntity.ok(ownerService.save(owner));
    }

    @GetMapping("/{id}/notes")
    public ResponseEntity<List<Note>> getNotes(@PathVariable long id){
        List<Note> list = ownerService.getNotes(id);

        if(list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

}
