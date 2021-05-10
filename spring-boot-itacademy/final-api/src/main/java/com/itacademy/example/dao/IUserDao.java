package com.itacademy.example.dao;

import com.itacademy.example.entity.User;

public interface IUserDao extends IAGenericDao<User>{

    User getByName(String username);
}
