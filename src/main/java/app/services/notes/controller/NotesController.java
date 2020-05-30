package app.services.notes.controller;

import app.services.notes.entity.Note;
import app.services.notes.entity.User;
import app.services.notes.repository.NotesRepository;
import app.services.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class NotesController {
	
	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private UserRepository userRepository;

	public NotesController(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}

	@PostMapping("/notes")
	@ResponseStatus(HttpStatus.CREATED)
	public Note addNote(@RequestBody Note note, Principal principal) {
		String email = principal.getName();
		User user = userRepository.findByEmail(email);
		note.setUser(user);
		return  notesRepository.save(note);
	}
//
//	@GetMapping("/notes")
//	public List<Note> getNotes() {
//		Iterable<Note> notesIterable = notesRepository.findAll();
//		List<Note> notes = new ArrayList<>();
//		notesIterable.forEach(notes::add);
//		return notes;
//	}

	@GetMapping("/user-notes")
	public List<Note> getNotesByUser(Principal principal) {
		String email = principal.getName();

		User user = userRepository.findByEmail(email);

		Iterable<Note> notesIterable = notesRepository.findByUser(user);
		List<Note> notes = new ArrayList<>();
		notesIterable.forEach(notes::add);
		return notes;
	}
}