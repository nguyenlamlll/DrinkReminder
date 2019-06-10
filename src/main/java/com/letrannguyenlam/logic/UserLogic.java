package com.letrannguyenlam.logic;

import com.letrannguyenlam.repositories.UserRepository;
import com.letrannguyenlam.repositories.models.User;

public class UserLogic {
    private UserRepository userRepository;
    public  UserLogic() {
        userRepository = new UserRepository();
    }

    public User getUser(int userId) {
        return userRepository.getUser(userId);
    }

    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public void updateUser(int userId, double weight, double height) {
        var updatingUser = this.getUser(userId);
        updatingUser.setWeight(weight);
        updatingUser.setHeight(height);
        userRepository.updateUser(updatingUser);
    }

    public Boolean verifyUser(String username, String password) {
        var user = userRepository.getUser(username);
        if (user == null) {
            return false;
        }
        if (password.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public void createUser(User user){
        userRepository.createUser(user);
    }
}
