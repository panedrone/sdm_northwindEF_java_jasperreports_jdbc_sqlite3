package com.sqlite.northwindef.dto;

// Code generated by a tool. DO NOT EDIT.
// https://sqldalmaker.sourceforge.net/

public class Category  {

    private Integer categoryID;  // t
    private String categoryName;  // t
    private String description;  // t
    private byte[] picture;  // t

    public Integer getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPicture() {
        return this.picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}