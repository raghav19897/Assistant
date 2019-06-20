package com.raghav.assistant.controller;

import java.sql.SQLException;
import java.util.ArrayList;

class CommandController {

    private ArrayList<String> urls = new ArrayList<>();

    private void setUrls(String url){
        urls.add(url);
    }

    ArrayList getUrls(){
        return urls;
    }

    /*private String url;

    private void setUrl(String url){
        this.url = url;
    }

    String getUrl(){
        return url;
    }*/

    void setEnter(String command) throws SQLException {
        urls.clear();
        String[] inputArray = command.split(" ");
        StringBuilder search= new StringBuilder();
        StringBuilder buy= new StringBuilder();

        //search on google
        if (command.contains("search")) {
            int s;
            if (inputArray[1].equals("for")) {
                s = 2;
            }
            else {
                s = 1;
            }
            for (int i = s; i < inputArray.length; i++) {
                search.append(inputArray[i]).append('+');
            }
            search = new StringBuilder(search.toString().toLowerCase());
            //Desktop.getDesktop().browse(new URL("https://www.google.com/search?q="+search).toURI());
            //setUrl("https://www.google.com/search?q="+search);
            setUrls("https://www.google.com/search?q="+search);
        }

        //search for products on amazon and flipkart
        else if (command.contains("buy")) {
            for (int i = 1; i < inputArray.length; i++) {
                buy.append(inputArray[i]).append('+');
            }
            buy = new StringBuilder(buy.toString().toLowerCase());
            setUrls("https://www.amazon.in/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=" + buy);
            setUrls("https://www.flipkart.com/search?q=" + buy);
        }

        //upload file to google drive
        else if (command.contains("upload")) {
            //setUrl("https://drive.google.com");
            setUrls("https://drive.google.com");
        }


        //open website
        else if (command.contains(".")) {
            for (int i=0; i<inputArray.length; i++) {
                if (inputArray[i].contains(".")) {
                    setUrls("https://" +inputArray[i]);
                }
            }
        }

        //search movies db
        else if (command.contains("*")){
            System.out.println(command);
            command = command.toLowerCase();
            DataLink dataLink = new DataLink();
            StringBuilder movieName = new StringBuilder();
            for (int i=0; i<inputArray.length-1; i++) {
                movieName.append(inputArray[i]).append("%20");
            }
            String platform = dataLink.checkMoviesBD(command.substring(0,command.length()-2));
            System.out.println(platform);
            String movie = movieName.toString();
            if (platform.equals("prime")) {
                //setUrl("https://www.primevideo.com/search/ref=atv_nb_sr?phrase=" + movie);
                setUrls("https://www.primevideo.com/search/ref=atv_nb_sr?phrase=" + movie);
            }
            if (platform.equals("netflix")) {
                //setUrl("https://www.netflix.com/search/" + movie);
                setUrls("https://www.netflix.com/search/" + movie);
            }
            if (platform.equals("hotstar")){
                //setUrl("https://www.hotstar.com/search?q=" + movie);
                setUrls("https://www.hotstar.com/search?q=" + movie);
            }
            if (platform.equals("voot")){
                //setUrl("https://www.hotstar.com/search?q=" + movie);
                setUrls("https://www.voot.com/search/" + movie);
            }
            if (platform.equals("zee")){
                //setUrl("https://www.hotstar.com/search?q=" + movie);
                setUrls("https://www.zee5.com/search/result?q=" + movie);
            }
            if (platform.equals("sony")){
                //setUrl("https://www.hotstar.com/search?q=" + movie);
                setUrls("https://www.sonyliv.com/search/" + movie);
            }
            if (platform.equals("hooq")){
                //setUrl("https://www.hotstar.com/search?q=" + movie);
                setUrls("https://m.hooq.tv/search?q=" + movie);
            }
        }

        //also search on google
        else {
            for (int i = 0; i < inputArray.length; i++) {
                search.append(inputArray[i]).append('+');
            }
            search = new StringBuilder(search.toString().toLowerCase());
            //setUrl("https://www.google.com/search?q="+search);
        }
    }
}