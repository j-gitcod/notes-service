package app.services.notes.repository;

import app.services.notes.entity.Note;
import app.services.notes.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface NotesRepository extends CrudRepository<Note, Integer> {

    Iterable<Note> findByUser(User user);

    @Query("SELECT n FROM Note n WHERE n.user.email = ?1")
    Collection<Note> findNotesByEmail(String email);
}