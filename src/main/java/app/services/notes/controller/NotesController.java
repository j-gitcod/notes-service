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
import java.util.*;
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
	public @ResponseBody Note addNote(@RequestBody Note note, Principal principal) {
		String email = principal.getName();
		User user = userRepository.findByEmail(email);
		note.setUser(user);
		return  notesRepository.save(note);
	}

	@GetMapping("/notes")
	public @ResponseBody List<Note> getNotesByUser(Principal principal) {
		String email = principal.getName();

		User user = userRepository.findByEmail(email);
		Iterable<Note> notesIterable = notesRepository.findByUser(user);
		List<Note> notes = new ArrayList<>();
		notesIterable.forEach(notes::add);
		return notes;
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Integer> deleteNote(@PathVariable Integer id, Principal principal) {
		String email = principal.getName();

		User user = userRepository.findByEmail(email);
		Note note = notesRepository.findById(id).orElse(null);
		if (note != null && note.getUser().equals(user)) {
			notesRepository.delete(note);
			return new ResponseEntity<>(id, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}