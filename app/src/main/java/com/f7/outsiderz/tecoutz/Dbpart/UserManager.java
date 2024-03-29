package com.f7.outsiderz.tecoutz.Dbpart;

import android.content.Context;

import com.f7.outsiderz.tecoutz.model.dao.DBUserDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by fajibfaaz on 09/03/17.
 */

public class UserManager {
    private static UserManager ourInstance;
    DBUserDAO userDAO;

    private UserManager(Context context) {

        this.userDAO = new DBUserDAO(context);
    }

    public static UserManager getInstance(Context context) {
        if(ourInstance == null)
            ourInstance = new UserManager(context);
        return ourInstance;
    }

    public boolean existUsername(String username){

        return userDAO.checkUsername(username);
    }

    public boolean existEmail(String email){

        return userDAO.checkUserEmail(email);
    }


    public boolean checkPasswordStrength(String password) {
        char[] test = password.toCharArray();

        boolean lowercase = false;
        boolean uppercase = false;
        boolean number = false;

        if(test.length < 8)
            return false;

        for (char aTest : test) {
            if (aTest >= 65 && aTest <= 90)
                lowercase = true;
            if (aTest >= 97 && aTest <= 122)
                uppercase = true;
            if (aTest >= 48 && aTest <= 57)
                number = true;
        }

        return !(!lowercase || !uppercase || !number);

    }

    public long register(UserAcc user){
        long id = userDAO.addUser(user);
        if(id != -1)
            user.setUserId(id);
        return id;
    }

    public UserAcc login(String email, String password){
        password = md5(password);

        return userDAO.checkLogin(email, password);
    }

    public boolean checkPassword(long userId, String password){
        password = md5(password);
        return userDAO.checkPassword(userId, password);
    }

    private static String md5(String password) {
        try {

            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public UserAcc getUser(long id){
        return userDAO.getUser(id);
    }

    public long updateEmail(long userId, String email){
        return userDAO.updateEmail(userId, email);
    }

    public long updatePassword(long userId, String password){
        password = md5(password);
        return userDAO.updatePassword(userId, password);
    }

    public long updatePersonalInfo(long userId, String username, String phone, String semester){
        return userDAO.updateUser(userId, username, phone, semester);
    }

    public long sendMessage(Message msg){
        return userDAO.sendMessage(msg);
    }

    public ArrayList<Message> getSentMessages(long userId){
        return userDAO.getSentMessages(userId);
    }

    public ArrayList<Message> getReceivedMessages(long userId){
        return userDAO.getReceivedMessages(userId);
    }

    public Message getMessage(long id){
        return userDAO.getMessage(id);
    }
}
