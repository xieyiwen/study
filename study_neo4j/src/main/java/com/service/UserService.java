package com.service;

import com.model.User;

import java.util.List;

/**
 * Created by 谢益文 on 2017/7/13.
 */
public interface UserService {

    public List<User> getFriends(User user);
    public void save(User user);
    public void batchSave(List<User> users);
}
