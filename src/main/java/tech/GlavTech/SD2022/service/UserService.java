package tech.GlavTech.SD2022.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.GlavTech.SD2022.exception.UserNotFoundException;
import tech.GlavTech.SD2022.model.User;
import tech.GlavTech.SD2022.repo.UserRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public User addUser(User user) {
        user.setUserCode(UUID.randomUUID().toString());
        return userRepo.save(user);
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id).orElseThrow(()-> new UserNotFoundException
                ("user by id " + id + " was not found"));
    }

    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email).orElseThrow(()-> new UserNotFoundException
                ("user by email " + email + " was not found"));
    }

    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username).orElseThrow(()-> new UserNotFoundException
                ("user by username " + username + " was not found"));
    }

    public void deleteUser(Long id) {
        userRepo.deleteUserById(id);
    }

    public void deleteFollowConnection(String current, String admiredUser) {
       User cur = userRepo.findUserByUsername(current).get();
       cur.getAdmiredUsers().remove(admiredUser);
    }


}
