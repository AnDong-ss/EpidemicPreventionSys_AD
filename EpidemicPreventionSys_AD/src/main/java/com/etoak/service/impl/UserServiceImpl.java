package com.etoak.service.impl;


import com.etoak.bean.PageVo;
import com.etoak.bean.User;
import com.etoak.bean.User2;
import com.etoak.mapper.UserMapper;
import com.etoak.service.UserService2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService2 {


    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> queryYCallUser() {
        return userMapper.queryYCallUser();
    }

    @Override
    public PageVo<User> list(Integer pageNumber, Integer pageSize) {
        //设置分页条件
        PageHelper.startPage(pageNumber,pageSize);
        //查询数据
        List<User> carList = userMapper.list();
        //创建pageInfo
        PageInfo<User> pageInfo = new PageInfo<>(carList);


        //返回数据
        PageVo<User> carPageVo = new PageVo<>(
                pageInfo.getPageNum(),
                pageInfo.getPageSize(),
                carList,
                pageInfo.getTotal(),
                pageInfo.getPages(),
                pageInfo.isIsFirstPage(),
                pageInfo.isIsLastPage());
        return carPageVo;
    }


    @Override
    public void add(User user1) {
        userMapper.add(user1);
    }

    @Override
    public void add2(User2 user2) {
        userMapper.add2(user2);
    }
}
