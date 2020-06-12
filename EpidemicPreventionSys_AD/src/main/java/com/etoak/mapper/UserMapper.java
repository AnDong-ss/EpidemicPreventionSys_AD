package com.etoak.mapper;

import com.etoak.bean.User;
import com.etoak.bean.User2;

import java.util.List;

public interface UserMapper {
    public List<User> queryYCallUser();

    public void add(User user);

    public void add2(User2 user);

    List<User> list();
}
