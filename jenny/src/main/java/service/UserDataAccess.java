package service;// Copyright (c) 2018 Travelex Ltd

import model.User;

import java.util.Optional;

public interface UserDataAccess {


    public Optional<User> getUser(Integer userId);

}
