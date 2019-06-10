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
}
