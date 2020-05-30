package app.services.notes;

import app.services.notes.entity.Note;
import app.services.notes.entity.User;
import app.services.notes.repository.NotesRepository;
import app.services.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AppStartupRunner implements ApplicationRunner {


    @Autowired
    UserRepository userRepo;

    @Autowired
    NotesRepository notesRepo;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        User userOne = new User("userone@demo.com", "password1");
        User userTwo = new User("usertwo@demo.com", "password2");
        User userThree = new User("userthree@demo.com", "password3");
        Set<User> users = Set.of(userOne, userTwo, userThree);

        userRepo.deleteAll();
        userRepo.saveAll(users);

        Note noteOne = new Note("note title 1","note description 1",userOne);
        Set<Note> notes = Set.of(noteOne);

        notesRepo.deleteAll();
        notesRepo.saveAll(notes);

    }
}