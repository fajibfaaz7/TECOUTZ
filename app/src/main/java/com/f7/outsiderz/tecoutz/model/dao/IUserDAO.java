package com.f7.outsiderz.tecoutz.model.dao;

import com.f7.outsiderz.tecoutz.Dbpart.Message;
import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fajibfaaz on 09/03/17.
 */

public interface IUserDAO {

    long addUser(UserAcc user);
    UserAcc getUser(String username);
    UserAcc getUser(long id);
    List<UserAcc> getAllUsers();
    void deleteUser(UserAcc user);
    long updateUser(UserAcc user);
    long updateUser(long userId, String username, String phone, String semester);
    boolean checkUsername(String username);
    boolean checkUserEmail(String email);
    boolean checkPassword(long userId, String password);
    UserAcc checkLogin(String email, String password);
    long updateEmail(long userId, String email);
    long updatePassword(long userId, String password);
    long sendMessage(Message msg);
    ArrayList<Message> getSentMessages(long userId);
    ArrayList<Message> getReceivedMessages(long userId);
    Message getMessage(long id);

}

