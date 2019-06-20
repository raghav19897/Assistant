package com.raghav.assistant;

import java.util.Date;

public class Home {
    private String user;
    private String password;
    private String name;
    private String command;
    private String notes;
    private String reminder;
    private java.sql.Date date;
    private String time;

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public String getCommand() {
        return command;
    }

    public String getNotes() {
        return notes;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReminder() {
        return reminder;
    }
}
