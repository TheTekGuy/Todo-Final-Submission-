package com.example.usmanhussain.Todoapp;
import java.util.Date;
import java.util.UUID;
import java.text.*;

public class Todo {

    private UUID mId;
    private String mTitle;
    private String mDetail;
    private String mDate;
    private boolean mIsComplete;

    public Todo() {
        mId = UUID.randomUUID();
        Date date = new Date();
        SimpleDateFormat fd = new SimpleDateFormat("E dd.MM.yyyy 'at' hh:mm:ss a ");
        mDate = fd.format(date);
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public void setComplete(boolean complete) {
        mIsComplete = complete;
    }

}