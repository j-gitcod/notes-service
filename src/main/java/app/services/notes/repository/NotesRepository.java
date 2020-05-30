package app.services.notes.repository;

import app.services.notes.entity.Note;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Note, Integer> {

}