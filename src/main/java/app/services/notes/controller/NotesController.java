package app.services.notes.controller;

import app.services.notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotesController {
	
	@Autowired
	private NotesRepository notesRepository;

	public NotesController(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}
    
}