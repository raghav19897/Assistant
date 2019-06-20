package com.raghav.assistant.controller;

import com.raghav.assistant.Home;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class HomeController {

    private Home home = new Home();
    private DataLink dataLink = new DataLink();
    private Encrypt encrypt = new Encrypt();
    private String name;
    private String user;
    private String password;

    private ModelAndView indexModel = new ModelAndView("index","login",home);
    private ModelAndView createAccountModel = new ModelAndView("createAccount","create",home);
    private ModelAndView homeModel = new ModelAndView("home","login",home);

    private Timer timer = new Timer();

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        if(!dataLink.checkReminder(user).equals("")) {
                            //to do when reminder starts.
                            File reminderFile = new File("/home/raghav/IdeaProjects/Assistant/src/main/webapp/resources/"+user+".json");
                            FileWriter fw = new FileWriter(reminderFile);
                            fw.write("{\"reminders\":\""+dataLink.checkReminder(user)+"\"}");
                            fw.close();
                        }
                        else {
                            File reminderFile = new File("/home/raghav/IdeaProjects/Assistant/src/main/webapp/resources/"+user+".json");
                            FileWriter fw = new FileWriter(reminderFile);
                            fw.write("{\"reminders\":\""+"No reminders"+"\"}");
                            fw.close();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            },0,1000);
        }
    };

    private Thread thread = new Thread(r);

    @RequestMapping("/")
    public ModelAndView index(){
        return indexModel;
    }

    @RequestMapping("/createAccount")
    public ModelAndView createAccount(@ModelAttribute Home home){
        return createAccountModel;
    }

    @RequestMapping("/create")
    public ModelAndView create(@ModelAttribute Home home) {
        name = home.getName();
        user = home.getUser();
        password = home.getPassword();
        if(!(name.equals("") || user.equals("") || password.equals(""))) {
            try {
                password = encrypt.encrypt(password);
                dataLink.insert(user, password, name);
                return indexModel;
            }
            catch (SQLException e) {
                createAccountModel.addObject("error","Email already exists");
                return createAccountModel;
            }
        }
        else{
            createAccountModel.addObject("error","Fields can not be empty");
            return createAccountModel;
        }
    }

    @RequestMapping("/login")
    public ModelAndView login(@ModelAttribute Home home) throws SQLException {
        user = home.getUser();
        password = home.getPassword();
        password = encrypt.encrypt(password);
        if (dataLink.validate(user,password)) {
            name = dataLink.getName();
            homeModel=homeModel.addObject("user",user);
            homeModel=homeModel.addObject("name",name);
            homeModel.addObject("notes",dataLink.setNotes(user));
            try {
                thread.start();
            }
            catch (Exception e){
                System.out.println(thread.getState());
            }
            return homeModel;
        }
        else {
            indexModel.addObject("error","Invalid credentials");
            return indexModel;
        }
    }

    @RequestMapping("/command")
    public ModelAndView setCommand(@ModelAttribute Home home) throws SQLException {
        String command = home.getCommand();
        CommandController commandController = new CommandController();
        commandController.setEnter(command);
        ArrayList url = commandController.getUrls();
        if(url.size()>0) {
            for (int i = 0; i < url.size(); i++) {
                homeModel.addObject("link" + i, url.get(i));
            }
            homeModel.addObject("number",url.size());
        }
        return homeModel;
    }

    @RequestMapping("/saveNotes")
    public ModelAndView addNotes(@ModelAttribute Home home) throws SQLException {
        String notes = home.getNotes();
        dataLink.updateNotes(user, notes);
        homeModel.addObject("notes",dataLink.setNotes(user));
        return homeModel;
    }

    @RequestMapping("/reminderSet")
    public ModelAndView setReminder(@ModelAttribute Home home) throws SQLException {
        String reminder = home.getReminder();
        Date date = (Date) home.getDate();
        String time = home.getTime();
        dataLink.setReminder(user, reminder, date, time);
        return homeModel;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(@ModelAttribute Home home){
        thread.interrupt();
        return new ModelAndView("index","login",home);
    }
}
