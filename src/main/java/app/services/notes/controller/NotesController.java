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
	public @ResponseBody app.services.notes.objects.Note addNote(@RequestBody Note note, Principal principal) {
		String email = principal.getName();
		User user = userRepository.findByEmail(email);
		note.setUser(user);
		note =  notesRepository.save(note);
		return new app.services.notes.objects.Note(note.getId(),note.getTitle(),note.getDescription(),note.getCreated(),note.getUpdated());
	}

	@GetMapping("/notes")
	public @ResponseBody List<app.services.notes.objects.Note> getNotesByUser(Principal principal) {
		String email = principal.getName();

		User user = userRepository.findByEmail(email);
		Iterable<Note> notesIterable = notesRepository.findNotesByEmail(email);
		List<app.services.notes.objects.Note> notes = new ArrayList<>();
		notesIterable.forEach(n -> {
			notes.add(new app.services.notes.objects.Note(n.getId(),n.getTitle(),n.getDescription(),n.getCreated(),n.getUpdated()));
		});
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