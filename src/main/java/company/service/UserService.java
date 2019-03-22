package company.service;

import company.model.User;
import company.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public boolean addUser(User user){
        User userFromDB = userRepo.findByUsername(user.getUsername());
        if(userFromDB != null) {
            return false;
        }
        userRepo.save(user);
        return true;
    }

    public User returnUserByUserName(String username){
        return userRepo.findByUsername(username);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userRepo=" + userRepo +
                '}';
    }
}
