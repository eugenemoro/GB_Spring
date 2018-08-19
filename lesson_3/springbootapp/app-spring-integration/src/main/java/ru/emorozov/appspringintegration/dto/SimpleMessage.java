package ru.emorozov.appspringintegration.dto;

import java.util.Date;

public class SimpleMessage {

    private Date date = new Date();

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
