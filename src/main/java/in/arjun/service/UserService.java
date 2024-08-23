package in.arjun.service;

import in.arjun.entity.User;

import java.util.Optional;

public interface UserService {

    boolean createUser(User user);

    Optional<User> getByEmailAndPassword(String email,String password);
}
