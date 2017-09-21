package com.service.impl;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 谢益文 on 2017/7/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> getFriends(User user){
        List<User> friends = userRepository.getFriends(user);
        if(friends != null && friends.size() > 0){
            return friends;
        }
        return null;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void batchSave(List<User> users) {
        userRepository.save(users);
    }

}
