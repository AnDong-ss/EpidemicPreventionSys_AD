package com.etoak.service;

import com.etoak.bean.PageVo;
import com.etoak.bean.User;
import com.etoak.bean.User2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService2 {
    public void add(User user);

    public void add2(User2 user);

    PageVo<User> list(Integer pageNumber, Integer pageSize);

    List<User> queryYCallUser();
}
