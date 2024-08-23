package in.arjun.service;

import in.arjun.entity.User;
import in.arjun.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUser(User user) {

        Optional<User> byEmailAndPassword = userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());

        if(byEmailAndPassword.isPresent()){
            return false;
        } else {
            userRepository.save(user);
            return true;
        }


    }

    @Override
    public Optional<User> getByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);

    }
}
