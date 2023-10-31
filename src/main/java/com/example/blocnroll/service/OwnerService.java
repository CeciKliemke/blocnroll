package com.example.blocnroll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.blocnroll.entities.Owner;
import com.example.blocnroll.entities.Note;
import com.example.blocnroll.repository.OwnerRepo;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepo ownerRepo;

    public Owner save(Owner owner) {
        return ownerRepo.save(owner);
    }

    public List<Note> getNotes(long id) {
        Optional<Owner> optionalOwner = ownerRepo.findById(id);
        if(optionalOwner.isEmpty()){
            return new ArrayList<>();
        }
        return optionalOwner.get().getNotes();
    }
}

}
