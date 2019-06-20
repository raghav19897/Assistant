package com.raghav.assistant.controller;

class Encrypt {

    String encrypt(String plainPass){
        StringBuilder cryptPass = new StringBuilder();
        char[] plainPassArray = plainPass.toCharArray();
        for (char c : plainPassArray) {
            cryptPass.append(c + 3);
        }
        return cryptPass.toString();
    }
}
