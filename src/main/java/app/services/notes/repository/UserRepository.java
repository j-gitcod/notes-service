package app.services.notes.repository;

import app.services.notes.entity.Note;
import app.services.notes.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

}