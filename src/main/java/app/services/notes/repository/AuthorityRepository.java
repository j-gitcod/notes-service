package app.services.notes.repository;

import app.services.notes.entity.Authority;
import app.services.notes.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

}