package com.f7.outsiderz.tecoutz.Dbpart;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;



public class Offer implements Comparable<Offer> {



    public enum Category {
        BOOKS, INSTRUMENTS, PHOTOSTATS, ACCESSORIES, OTHERS
    }

    public enum ProductCondition {
        NEW, USED
    }

    private long id;
    private String name;
    private String description;
    private double price;
    private UserAcc seller;
    private String condition;
    private String category;
    private String semester;
    private boolean isActive;
    private Date creationDate;
    private Date expiryDate;
    private ArrayList<byte[]> images;

    public Offer(UserAcc seller, String name, String description, double price, String productCondition, String category, String semester, boolean isActive, ArrayList<byte[]> images, Date date) throws IllegalArgumentException {
        this.seller = seller;
        this.setPrice(price);
        this.name = name;
        this.setDescription(description);
        this.condition = productCondition;
        this.category = category;
        this.creationDate = new Date();
            Calendar dateExpire = Calendar.getInstance();
            dateExpire.add(Calendar.MONTH, 1);
        this.expiryDate = dateExpire.getTime();
        this.isActive = isActive;
        this.semester = semester;
        this.creationDate = date;
        this.images = images;
    }

    public boolean checkIfActive() {
        return isActive;
    }

    public void autoSetIfActive() {
        if (expiryDate.after(new Date())) {
            isActive = false;
        }
    }

    public void switchIfActive(boolean isActive){
        this.isActive = isActive;
    }

    public void changeExpireDay() {
        Calendar newDateExpire = Calendar.getInstance();
        newDateExpire.setTime(expiryDate);
        newDateExpire.add(Calendar.MONTH, 1);
        expiryDate = newDateExpire.getTime();
        isActive = true;
    }

    @Override
    public int compareTo(@NonNull Offer another) {
        if(creationDate.after(another.creationDate))
            return 1;
        if(creationDate.before(another.creationDate))
            return -1;

        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws IllegalArgumentException {
        if(price < 0)
            throw new IllegalArgumentException();
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSemester() { return semester; }

    public void setSemester(String semester) {this.semester = semester;}

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<byte[]> getImages() {
        return images;
    }

    public UserAcc getSeller() {
        return this.seller;
    }
}
