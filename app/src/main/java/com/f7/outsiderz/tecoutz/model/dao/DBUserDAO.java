package com.f7.outsiderz.tecoutz.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.f7.outsiderz.tecoutz.Dbpart.Message;
import com.f7.outsiderz.tecoutz.Dbpart.UserAcc;
import com.f7.outsiderz.tecoutz.model.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fajibfaaz on 09/03/17.
 */

public class  DBUserDAO implements IUserDAO {
    private DatabaseHelper mDb;
    private Context context;

    public DBUserDAO(Context context){
        this.context = context;
        this.mDb = DatabaseHelper.getInstance(context);
    }

    @Override
    public long addUser(UserAcc user) {
        SQLiteDatabase db = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(mDb.USERNAME, user.getUserName());
        values.put(mDb.EMAIL, user.getEmail().toString());
        values.put(mDb.PASSWORD, user.getPassword());
        values.put(mDb.SEMESTER, user.getSemester());
        values.put(mDb.TELEPHONE, user.getPhoneNumber());


        long userId = db.insert(mDb.USERS, null, values);
        db.close();
        return userId;
    }

    @Override
    public UserAcc getUser(String username) {
        SQLiteDatabase db = mDb.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + mDb.USERS
                + "WHERE " + mDb.USERNAME + " = \"" + username + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        long userId = c.getLong(c.getColumnIndex(mDb.USER_ID));
        String uname = c.getString(c.getColumnIndex(mDb.USERNAME));
        String password = c.getString(c.getColumnIndex(mDb.PASSWORD));
        String email = c.getString(c.getColumnIndex(mDb.EMAIL));
        String semester = c.getString(c.getColumnIndex(mDb.SEMESTER));
        String phone = c.getString(c.getColumnIndex(mDb.TELEPHONE));


        UserAcc user = new UserAcc(uname, password, email, semester, phone);
        user.setUserId(userId);
        c.close();
        db.close();
        return user;
    }

    @Override
    public UserAcc getUser(long id) {
        SQLiteDatabase db = mDb.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + mDb.USERS
                + " WHERE " + mDb.USER_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        String uname = c.getString(c.getColumnIndex(mDb.USERNAME));
        String password = c.getString(c.getColumnIndex(mDb.PASSWORD));
        String email = c.getString(c.getColumnIndex(mDb.EMAIL));
        String phone = c.getString(c.getColumnIndex(mDb.TELEPHONE));
        String semester = c.getString(c.getColumnIndex(mDb.SEMESTER));

        UserAcc user = new UserAcc(email, password, uname, phone, semester);
        user.setUserId(id);
        c.close();
        db.close();
        return user;
    }

    @Override
    public List<UserAcc> getAllUsers() {
        ArrayList<UserAcc> users = new ArrayList<UserAcc>();
        String query = "SELECT * FROM " + mDb.USERS;

        SQLiteDatabase db = mDb.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                String uname = c.getString(c.getColumnIndex(mDb.USERNAME));
                String password = c.getString(c.getColumnIndex(mDb.PASSWORD));
                String email = c.getString(c.getColumnIndex(mDb.EMAIL));
                String phone = c.getString(c.getColumnIndex(mDb.TELEPHONE));
                String semester = c.getString(c.getColumnIndex(mDb.SEMESTER));

                UserAcc user = new UserAcc(uname, password, email, phone, semester);
                users.add(user);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return users;
    }

    @Override
    public void deleteUser(UserAcc user) {
        SQLiteDatabase db = mDb.getWritableDatabase();
        db.delete(mDb.USERS, mDb.USERNAME + " = ?",
                new String[]{user.getUserName()});

        db.close();
    }

    @Override
    public long updateUser(UserAcc user) {
        SQLiteDatabase db = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(mDb.USERNAME, user.getUserName());
        values.put(mDb.EMAIL, user.getEmail().toString());
        values.put(mDb.PASSWORD, user.getPassword());
        values.put(mDb.TELEPHONE, user.getPhoneNumber());
        values.put(mDb.SEMESTER, user.getSemester());

        long userId = db.update(mDb.USERS, values, mDb.USERNAME + " = ? ", new String[]{user.getUserName()});
        db.close();
        return userId;
    }

    @Override
    public long updateUser(long userId, String username, String phone, String semester) {
        SQLiteDatabase db = mDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(mDb.USERNAME, username);
        values.put(mDb.SEMESTER, semester);
        values.put(mDb.TELEPHONE, phone);

        long result = db.update(mDb.USERS, values, mDb.USER_ID + " = ? ", new String[]{String.valueOf(userId)});
        db.close();
        return result;
    }

    @Override
    public boolean checkUsername(String username) {
        SQLiteDatabase db = mDb.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + mDb.USERS
                + " WHERE " + mDb.USERNAME + " = \"" + username + "\"";

        Cursor c = db.rawQuery(selectQuery, null);


        if (c != null && c.moveToFirst()) {
            db.close();
            c.close();
            return true;
        }
        else{
            db.close();
            c.close();
            return false;
        }
    }

    @Override
    public boolean checkUserEmail(String email) {
        SQLiteDatabase db = mDb.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + mDb.USERS
                + " WHERE " + mDb.EMAIL + " = \"" + email + "\"";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }

    @Override
    public UserAcc checkLogin (String email, String password) {
        SQLiteDatabase db = mDb.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + mDb.USERS
                + " WHERE " + mDb.EMAIL + " = \"" + email
                + "\" AND " + mDb.PASSWORD + " = \"" + password + "\"";

        Cursor c = db.rawQuery(selectQuery, null);


        UserAcc user = null;

        if(c.moveToFirst()){
            long id = c.getLong(c.getColumnIndex(mDb.USER_ID));
            String uname = c.getString(c.getColumnIndex(mDb.USERNAME));
            String upassword = c.getString(c.getColumnIndex(mDb.PASSWORD));
            String uemail = c.getString(c.getColumnIndex(mDb.EMAIL));
            String semester = c.getString(c.getColumnIndex(mDb.SEMESTER));
            String phone = c.getString(c.getColumnIndex(mDb.TELEPHONE));

            user = new UserAcc(uemail, upassword, uname, phone, semester);
            user.setUserId(id);
        }

        c.close();
        db.close();
        return user;
    }

    public boolean checkPassword(long userID, String password) {
        SQLiteDatabase db = mDb.getReadableDatabase();
        String query = "SELECT " + mDb.PASSWORD +" FROM "+ mDb.USERS +" WHERE "+ mDb.USER_ID +" = " + userID;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String pass = c.getString(c.getColumnIndex(mDb.PASSWORD));
        c.close();
        if (password.equals(pass)) {
            return true;
        } else
            return false;

    }

    public long updateEmail(long userId, String email){
        SQLiteDatabase db = mDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(mDb.EMAIL, email);

        long result = db.update(mDb.USERS, values, mDb.USER_ID + " = ?", new String[]{(String.valueOf(userId))});
        return result;
    }

    public long updatePassword(long userId, String password){
        SQLiteDatabase db = mDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(mDb.PASSWORD, password);

        long result = db.update(mDb.USERS, values, mDb.USER_ID + " = ?", new String[]{(String.valueOf(userId))});
        return result;
    }

    @Override
    public long sendMessage(Message msg) {
        SQLiteDatabase db = mDb.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(mDb.SENDER_ID, msg.getSenderId());
        values.put(mDb.RECEIVER_ID, msg.getReceiverId());
        values.put(mDb.TITLE, msg.getHeading());
        values.put(mDb.CONTENT, msg.getText());
        values.put(mDb.DATE, String.valueOf(msg.getDate()));

        long id = db.insert(mDb.MESSAGES, null, values);
        return id;
    }

    @Override
    public ArrayList<Message> getSentMessages(long userId){
        SQLiteDatabase db = mDb.getReadableDatabase();
        String query = "SELECT * FROM " + mDb.MESSAGES
                + " WHERE " + mDb.SENDER_ID + " = " + userId;
        Cursor c = db.rawQuery(query, null);
        ArrayList<Message> messages = new ArrayList<Message>();

        if(c.moveToFirst()){
            do{
                long messageId = c.getLong(c.getColumnIndex(mDb.MESSAGE_ID));
                long receiverId = c.getLong(c.getColumnIndex(mDb.RECEIVER_ID));
                String title = c.getString(c.getColumnIndex(mDb.TITLE));
                String content = c.getString(c.getColumnIndex(mDb.CONTENT));
                String date =  c.getString(c.getColumnIndex(mDb.DATE));
                Message msg = new Message(messageId, userId, receiverId, title, content);
                messages.add(msg);
            }
            while (c.moveToNext());
        }

        db.close();
        return messages;
    }

    @Override
    public ArrayList<Message> getReceivedMessages(long userId){
        SQLiteDatabase db = mDb.getReadableDatabase();
        String query = "SELECT * FROM " + mDb.MESSAGES
                + " WHERE " + mDb.RECEIVER_ID + " = " + userId;
        Cursor c = db.rawQuery(query, null);
        ArrayList<Message> messages = new ArrayList<Message>();

        if(c.moveToFirst()){
            do{
                long messageId = c.getLong(c.getColumnIndex(mDb.MESSAGE_ID));
                long senderId = c.getLong(c.getColumnIndex(mDb.SENDER_ID));
                String title = c.getString(c.getColumnIndex(mDb.TITLE));
                String content = c.getString(c.getColumnIndex(mDb.CONTENT));
                String date =  c.getString(c.getColumnIndex(mDb.DATE));
                Message msg = new Message(messageId, senderId, userId, title, content);
                messages.add(msg);
            }
            while (c.moveToNext());
        }

        db.close();
        return messages;
    }

    @Override
    public Message getMessage(long id){
        SQLiteDatabase db = mDb.getReadableDatabase();
        String query = "SELECT * FROM " + mDb.MESSAGES
                + " WHERE " + mDb.MESSAGE_ID + " = " + id;
        Message m = null;
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            long senderId = c.getLong(c.getColumnIndex(mDb.SENDER_ID));
            long receiverId = c.getLong(c.getColumnIndex(mDb.RECEIVER_ID));
            String title = c.getString(c.getColumnIndex(mDb.TITLE));
            String content = c.getString(c.getColumnIndex(mDb.CONTENT));
            String date = c.getString(c.getColumnIndex(mDb.DATE));

            m = new Message(id, senderId, receiverId, title, content);
        }
        c.close();
        db.close();
        return m;
    }
}


