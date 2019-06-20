package com.raghav.assistant.controller;

import java.sql.*;
import java.util.Calendar;

class DataLink {

    private Statement stmt;
    private Statement stmtRem;
    private Statement stmtMovie;
    private String name;
    private Connection conn;
    private Connection connMovie;

    private void connect() throws SQLException {
        String url = "jdbc:sqlite:src/main/resources/assistantDatabase.db";
        String urlRem = "jdbc:sqlite:src/main/resources/reminderDatabase.db";
        String urlMovie = "jdbc:sqlite:src/main/resources/moviesnewfull.db";
        connMovie = DriverManager.getConnection(urlMovie);
        conn = DriverManager.getConnection(url);
        Connection connRem = DriverManager.getConnection(urlRem);
        stmt = conn.createStatement();
        stmtRem = connRem.createStatement();
        stmtMovie = connMovie.createStatement();
    }

    void insert(String user, String password, String name) throws SQLException {
        connect();
        stmt.execute("insert into login values(\"" + user + "\",\"" + password + "\",\"" + name + "\")");
        stmt.execute("insert into notes values(\"" + user + "\",\"" + "" + "\")");
        conn.close();
    }

    String getName(){
        return name;
    }

    boolean validate(String user, String password) throws SQLException{
        connect();
        boolean found = false;
        ResultSet resultSet;
        resultSet = stmt.executeQuery("select * from login where User_ID = \"" + user + "\"");
        while (resultSet.next()){
            if(password.equals(resultSet.getString("Password"))){
                found = true;
                name = resultSet.getString("Name");
                break;
            }
        }
        conn.close();
        return found;
    }

    String setNotes(String user) throws SQLException {
        connect();
        StringBuilder notes = new StringBuilder();
        ResultSet rs = stmt.executeQuery("select Notes from notes where User_ID=\""+user+"\"");
        while (rs.next()){
            notes.append(rs.getString("Notes"));
        }
        return notes.toString();
    }

    void updateNotes(String user, String notes) throws SQLException {
        connect();
        stmt.executeUpdate("update notes set Notes=\""+ notes +"\" where User_ID=\"" + user + "\"");
        conn.close();
    }

    void setReminder(String user, String reminder, Date date, String time) throws SQLException {
        connect();
        stmtRem.execute("insert into reminders values(\""+user+"\",\""+reminder+"\",\""+date+"\",\""+time+"\")");
        System.out.println("reminder set");
        conn.close();
    }

    String checkReminder(String user) throws SQLException {
        String reminder="";
        String date="";
        String time="";
        int mo = Calendar.getInstance().get(Calendar.MONTH)+1;
        int mi =Calendar.getInstance().get(Calendar.MINUTE);
        String month="";
        String minute="";
        if(mo<10){
            month = "0"+mo;
        }
        else {
            month = String.valueOf(mo);
        }
        if(mi<10){
            minute = "0"+mi;
        }
        else {
            minute = String.valueOf(mi);
        }
        date = Calendar.getInstance().get(Calendar.YEAR)+"-"+month+"-"+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)+":"+minute;
        connect();
        ResultSet rs;
        rs = stmtRem.executeQuery("select Reminders from reminders where Time=\""+time+"\" and Date=\""+date+"\" and User_ID=\""+user+"\"");
        while (rs.next()){
            reminder = rs.getString("Reminders");
        }
        return reminder;
    }

    String checkMoviesBD(String movieName) throws SQLException {
        connect();
        String platform="";
        ResultSet rs;
        rs = stmtMovie.executeQuery("select * from moviestv where Name like \"%"+movieName+"%\"");
        while (rs.next()){
            platform = rs.getString("VoDPlatform");
        }
        connMovie.close();
        return platform;
    }
}
