package app.services.notes;

import app.services.notes.entity.User;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User userOne = new User("userone@demo.com", "passone");
        User userTwo = new User("usertwo@demo.com", "passtwo");
        User userThree = new User("userthree@demo.com", "passthree");
        Set<User> users = Set.of(userOne, userTwo, userThree);

        userRepo.deleteAll();
        userRepo.saveAll(users);
    }
}