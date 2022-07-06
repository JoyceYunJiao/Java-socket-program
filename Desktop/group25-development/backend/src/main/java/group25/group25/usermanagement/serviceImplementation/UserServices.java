package group25.group25.usermanagement.serviceImplementation;

import group25.group25.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public boolean login(String email, String password) {

        return false;
    }
}
