package app.services.notes.controller;

import app.services.notes.entity.Note;
import app.services.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Controller
public class NotesController {
	
	@Autowired
	private NotesRepository notesRepository;

	public NotesController(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}

	@PostMapping("/notes")
	@ResponseStatus(HttpStatus.CREATED)
	public Note addNote(@RequestBody Note note) {
		return  notesRepository.save(note);
	}

	@GetMapping("/notes")
	public Iterable<Note> getNotes() {
		return notesRepository.findAll();
	}

}